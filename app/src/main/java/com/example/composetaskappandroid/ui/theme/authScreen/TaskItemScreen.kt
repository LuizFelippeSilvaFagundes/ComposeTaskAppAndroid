package com.example.composetaskappandroid.ui.theme

import Task
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TaskItem(
    task: Task,
    onEdit: (Task) -> Unit,
    onDelete: (Task) -> Unit,
    onToggleComplete: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Checkbox(
            checked = task.isCompleted,
            onCheckedChange = { onToggleComplete() }
        )
        Text(
            text = task.title,
            modifier = Modifier.weight(1f)
        )
        Button(onClick = { onEdit(task) }) { Text("Edit") }
        Button(onClick = { onDelete(task) }) { Text("Delete") }
    }
}
