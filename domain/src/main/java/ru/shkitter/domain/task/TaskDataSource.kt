package ru.shkitter.domain.task

import ru.shkitter.domain.task.model.Category
import ru.shkitter.domain.task.model.Priority
import ru.shkitter.domain.task.model.Task

interface TaskDataSource {
    suspend fun getTaskList(): List<Task>
    suspend fun getCategories(): List<Category>
    suspend fun getPriorities(): List<Priority>
}