package com.example.mytodoapp

import androidx.lifecycle.ViewModel

class ToDoViewModel() : ViewModel() {

    fun getAllItems(): List<Task> = TestDataRepository.tasks
}