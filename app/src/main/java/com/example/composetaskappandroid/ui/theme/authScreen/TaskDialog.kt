package com.example.composetaskappandroid.ui

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.composetaskappandroid.data.Task
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun TaskDialog(
    task: Task? = null,
    onDismiss: () -> Unit,
    onSave: (Task) -> Unit
) {
    var title by remember { mutableStateOf(task?.title ?: "") }
    var description by remember { mutableStateOf(task?.description ?: "") }
    var dueDateMillis by remember { mutableStateOf(task?.dueDate ?: System.currentTimeMillis()) }

    // Picker state
    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }

    if (showDatePicker) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(
            LocalContext.current,
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = Calendar.getInstance().apply {
                    set(Calendar.YEAR, selectedYear)
                    set(Calendar.MONTH, selectedMonth)
                    set(Calendar.DAY_OF_MONTH, selectedDay)
                }
                dueDateMillis = selectedDate.timeInMillis
                showDatePicker = false
            },
            year,
            month,
            day
        ).show()
    }

    if (showTimePicker) {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        TimePickerDialog(
            LocalContext.current,
            { _, selectedHour, selectedMinute ->
                val selectedTime = Calendar.getInstance().apply {
                    timeInMillis = dueDateMillis // Mantém a data selecionada
                    set(Calendar.HOUR_OF_DAY, selectedHour)
                    set(Calendar.MINUTE, selectedMinute)
                }
                dueDateMillis = selectedTime.timeInMillis
                showTimePicker = false
            },
            hour,
            minute,
            true
        ).show()
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = {
                if (title.isNotBlank()) {
                    onSave(Task(id = task?.id ?: 0, title = title, description = description, dueDate = dueDateMillis))
                }
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) { Text("Cancel") }
        },
        title = { Text("Tarefa") },
        text = {
            Column {
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = description ?: "",
                    onValueChange = { description = it },
                    label = { Text("Description") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(Date(dueDateMillis)),
                    onValueChange = {},
                    label = { Text("Due Date") },
                    enabled = false // Desabilitar edição direta
                )
                Button(onClick = { showDatePicker = true }) {
                    Text("Select Date")
                }
                Button(onClick = { showTimePicker = true }) {
                    Text("Select Time")
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    )
}
