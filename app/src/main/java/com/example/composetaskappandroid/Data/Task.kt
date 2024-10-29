package com.example.composetaskappandroid.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val description: String?, // Pode ser nulo
    val dueDate: Long? = null, // Armazenar como milissegundos
    val isCompleted: Boolean = false
)
