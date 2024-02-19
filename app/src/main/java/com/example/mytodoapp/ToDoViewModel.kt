package com.example.mytodoapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.mytodoapp.data.Task
import com.example.mytodoapp.data.TaskDao
import kotlinx.coroutines.launch

class ToDoViewModel(private val taskDao: TaskDao) : ViewModel() {

    val allTasks: LiveData<List<Task>> = taskDao.getAllTasks().asLiveData()

    private fun insertTask(task: Task) {
        viewModelScope.launch {
            taskDao.insert(task)
        }
    }

    private fun getNewTaskEntry(description: String): Task {
        return Task(
            description = description,
            complete = false
        )
    }

    fun retrieveTask(id: Int): LiveData<Task> {
        return taskDao.getTask(id).asLiveData()
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            taskDao.delete(task)
        }
    }

    fun addNewTask(description: String) {
        val newTask = getNewTaskEntry(description)
        insertTask(newTask)
    }

}

class TodoViewModelFactory(private val taskDao: TaskDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(ToDoViewModel::class.java)) {
            return ToDoViewModel(taskDao) as T
        } else throw IllegalStateException("Unknown ViewModel class")
    }
}