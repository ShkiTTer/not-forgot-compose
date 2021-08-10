package ru.shkitter.domain.task

import ru.shkitter.domain.task.model.Task

interface TaskDataSource {
    suspend fun getTaskList(): List<Task>
}