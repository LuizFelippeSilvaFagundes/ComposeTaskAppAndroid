
package com.example.composetaskappandroid.data

import android.content.Context
import androidx.room.Room

class DatabaseModule {

    fun getDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "app_database"
        )
            .addMigrations(AppDatabase.MIGRATION_1_2) // Adicione a migração aqui
            .build()
    }
}
