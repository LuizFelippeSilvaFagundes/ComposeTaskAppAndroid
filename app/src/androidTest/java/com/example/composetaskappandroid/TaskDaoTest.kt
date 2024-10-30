package com.example.composetaskappandroid

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.example.composetaskappandroid.data.AppDatabase
import com.example.composetaskappandroid.data.Task
import com.example.composetaskappandroid.data.TaskDao
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TaskDaoTest {

    private lateinit var db: AppDatabase
    private lateinit var taskDao: TaskDao

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            getApplicationContext(),
            AppDatabase::class.java
        ).build()
        taskDao = db.taskDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun testInsertAndGetTask() = runBlocking {
        val task = Task(title = "Test Task", description = "Description")
        taskDao.insertTask(task)

        val tasks = taskDao.getAllTasks()
        assertEquals(1, tasks.size)
        assertEquals("Test Task", tasks[0].title)
    }

    @Test
    fun testDeleteTask() = runBlocking {
        val task = Task(title = "Test Task", description = "Description")
        taskDao.insertTask(task)

        taskDao.deleteTask(task.id)

        val tasks = taskDao.getAllTasks()
        assertEquals(0, tasks.size)
    }
}
