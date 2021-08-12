package ru.shkitter.domain.task.model

data class CreateTaskData(
    val categories: List<Category>,
    val priorities: List<Priority>
)
