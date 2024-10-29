package com.example.composetaskappandroid.ui

import TaskViewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composetaskappandroid.data.Task
import com.example.composetaskappandroid.ui.TaskDialog
import com.example.composetaskappandroid.ui.theme.TaskItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp

@Composable
fun TaskListScreen(viewModel: TaskViewModel) {
    var showDialog by remember { mutableStateOf(false) }
    var taskToEdit by remember { mutableStateOf<Task?>(null) }

    // Estado para controlar a expansão/colapso das seções
    var showPendingTasks by remember { mutableStateOf(false) }
    var showCompletedTasks by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Text("+")
            }
        }
    ) { padding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(padding)) {
            // Seção de tarefas pendentes
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { showPendingTasks = !showPendingTasks } // Alterna a visibilidade
            ) {
                Text("Minhas Tarefas", style = MaterialTheme.typography.titleMedium)
                Icon(
                    imageVector = if (showPendingTasks) Icons.Filled.ArrowDropUp else Icons.Filled.ArrowDropDown,
                    contentDescription = "Toggle pending tasks",
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
            if (showPendingTasks) {
                LazyColumn {
                    items(viewModel.tasks.filter { !it.isCompleted }) { task ->
                        TaskItem(
                            task = task,
                            onEdit = { taskToEdit = it; showDialog = true },
                            onDelete = { viewModel.deleteTask(it) },
                            onToggleComplete = { viewModel.toggleTaskCompletion(task) }
                        )
                    }
                }
            }

            // Seção de tarefas finalizadas
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { showCompletedTasks = !showCompletedTasks } // Alterna a visibilidade
            ) {
                Text("Tarefas Finalizadas", style = MaterialTheme.typography.titleMedium)
                Icon(
                    imageVector = if (showCompletedTasks) Icons.Filled.ArrowDropUp else Icons.Filled.ArrowDropDown,
                    contentDescription = "Toggle completed tasks",
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
            if (showCompletedTasks) {
                LazyColumn {
                    items(viewModel.tasks.filter { it.isCompleted }) { task ->
                        TaskItem(
                            task = task,
                            onEdit = { taskToEdit = it; showDialog = true },
                            onDelete = { viewModel.deleteTask(it) },
                            onToggleComplete = { viewModel.toggleTaskCompletion(task) }
                        )
                    }
                }
            }
        }
    }

    if (showDialog) {
        TaskDialog(
            task = taskToEdit,
            onDismiss = { showDialog = false; taskToEdit = null },
            onSave = {
                if (taskToEdit != null) {
                    viewModel.updateTask(it)
                } else {
                    viewModel.addTask(it)
                }
                showDialog = false
                taskToEdit = null
            }
        )
    }
}
