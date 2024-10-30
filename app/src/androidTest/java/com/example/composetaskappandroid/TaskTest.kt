package com.example.composetaskappandroid

import com.example.composetaskappandroid.data.Task
import org.junit.Assert.assertEquals
import org.junit.Test

class TaskTest {

    @Test
    fun testTaskInitialization() {
        val task = Task(title = "Test Task", description = "Description", isCompleted = false)

        assertEquals("Test Task", task.title)
        assertEquals("Description", task.description)
        assertEquals(false, task.isCompleted)
    }

    @Test
    fun testTaskCompletionToggle() {
        val task = Task(title = "Test Task", description = "Description", isCompleted = false)

        val updatedTask = task.copy(isCompleted = true)
        assertEquals(true, updatedTask.isCompleted)
    }
}
