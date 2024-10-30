package com.example.composetaskappandroid

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.composetaskappandroid.data.Task

class MockTaskViewModel : ViewModel() {
    val tasks = mutableStateListOf<Task>()
    var advice = mutableStateOf("")

    fun addTask(task: Task) {
        tasks.add(task)
    }

    fun toggleTaskCompletion(task: Task) {
        val updatedTask = task.copy(isCompleted = !task.isCompleted)
        tasks[tasks.indexOf(task)] = updatedTask
    }
}
