package com.example.mytodoapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskRoomDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var INSTANCE: TaskRoomDatabase? = null
        fun getDatabase(context: Context): TaskRoomDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context, TaskRoomDatabase::class.java, "task_database"
                ).fallbackToDestructiveMigration().build()
                    .also { INSTANCE = it }
                return instance
            }
        }
    }
}