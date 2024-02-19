package com.example.mytodoapp

import android.app.Application
import com.example.mytodoapp.data.TaskRoomDatabase

class ToDoApplication() : Application() {
    val database: TaskRoomDatabase by lazy {
        TaskRoomDatabase.getDatabase(this)
    }
}