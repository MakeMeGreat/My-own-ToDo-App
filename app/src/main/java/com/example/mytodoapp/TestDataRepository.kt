package com.example.mytodoapp

import com.example.mytodoapp.data.Task

//test data repository
object TestDataRepository {
    val testTasks: List<Task> = listOf(
        Task(
            1, "Write poem", false
        ),
        Task(
            2, "Take breakfast", true
        ),
        Task(
            3, "Water flowers", false
        ),

        Task(
            4, "Complete homework", true
        ),
        Task(
            6,
            "1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0",
            false
        ),
        Task(
            5, "Clean up room", false
        ),

        )
}