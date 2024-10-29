package com.example.composetaskappandroid

import AuthViewModel
import RegisterScreen
import TaskListScreen
import TaskViewModel
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composetaskappandroid.data.AppDatabase
import com.example.composetaskappandroid.data.TaskDao
import com.example.composetaskappandroid.ui.LoginScreen
import com.example.composetaskappandroid.ui.theme.ComposeTaskAppAndroidTheme

class MainActivity : ComponentActivity() {
    private lateinit var taskDao: TaskDao

    @RequiresApi(Build.VERSION_CODES.O) // Apenas se você estiver usando Java Time
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar o banco de dados Room
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "task-database"
        )
            .addMigrations(AppDatabase.MIGRATION_2_3) // Adicione a migração aqui
            .fallbackToDestructiveMigration() // Remove dados se a migração falhar
            .build()

        // Obtenha o DAO
        taskDao = db.taskDao()

        setContent {
            ComposeTaskAppAndroidTheme {
                AppNavigator(taskDao)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O) // Apenas se você estiver usando Java Time
@Composable
fun AppNavigator(taskDao: TaskDao) {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()
    val taskViewModel = remember { TaskViewModel(taskDao) }

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("task_list") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate("register")
                }
            )
        }
        composable("register") {
            RegisterScreen(
                onRegister = { email, name, password, callback ->
                    authViewModel.registerUser(email, password) { isSuccess, message ->
                        callback(isSuccess, message)
                    }
                },
                onRegisterSuccess = {
                    navController.popBackStack() // Volta para a tela de login após o sucesso
                }
            )
        }
        composable("task_list") {
            TaskListScreen(viewModel = taskViewModel)
        }
    }
}
