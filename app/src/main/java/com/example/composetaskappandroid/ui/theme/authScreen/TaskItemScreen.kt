package com.example.composetaskappandroid.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composetaskappandroid.data.Task

@Composable
fun TaskItem(
    task: Task,
    onEdit: (Task) -> Unit,
    onDelete: (Task) -> Unit,
    onToggleComplete: () -> Unit,
    showRepeatInfo: @Composable (() -> Unit)? = null // Parâmetro opcional para mostrar info de repetição
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
        Column(modifier = Modifier.weight(1f)) {
            Text(text = task.title) // Título da tarefa
            Text(text = task.description ?: "Sem descrição") // Adiciona a descrição da tarefa
            showRepeatInfo?.invoke() // Chama a função para exibir o intervalo de repetição, se fornecida
        }
        Button(onClick = { onEdit(task) }) { Text("Edit") }
        Button(onClick = { onDelete(task) }) { Text("Delete") }
    }
}
