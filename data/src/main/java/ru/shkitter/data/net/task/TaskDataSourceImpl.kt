package ru.shkitter.data.net.task

import ru.shkitter.domain.task.TaskDataSource
import ru.shkitter.domain.task.model.Category
import ru.shkitter.domain.task.model.Priority
import ru.shkitter.domain.task.model.Task

class TaskDataSourceImpl(
    private val taskApi: TaskApi
) : TaskDataSource {

    override suspend fun getTaskList(): List<Task> {
        return taskApi.getTaskList().map { it.toDomain() }
    }

    override suspend fun getCategories(): List<Category> {
        return taskApi.getCategories().map { it.toDomain() }
    }

    override suspend fun getPriorities(): List<Priority> {
        return taskApi.getPriorities().map { it.toDomain() }
    }
}