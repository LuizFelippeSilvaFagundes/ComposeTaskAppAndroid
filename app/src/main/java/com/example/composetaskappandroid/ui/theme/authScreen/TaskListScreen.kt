import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.composetaskappandroid.data.Task
import com.example.composetaskappandroid.ui.TaskDialog
import com.example.composetaskappandroid.ui.theme.TaskItem

@Composable
fun TaskListScreen(viewModel: TaskViewModel) {
    var showDialog by remember { mutableStateOf(false) }
    var taskToEdit by remember { mutableStateOf<Task?>(null) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Text("+")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(viewModel.tasks) { task ->
                TaskItem(
                    task = task,
                    onEdit = { taskToEdit = it; showDialog = true },
                    onDelete = { viewModel.deleteTask(it) },
                    onToggleComplete = { viewModel.toggleTaskCompletion(task) } // Chama a função de alternar
                )
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
