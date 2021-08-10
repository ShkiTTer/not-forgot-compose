package ru.shkitter.data.net.task

import ru.shkitter.domain.task.TaskDataSource
import ru.shkitter.domain.task.model.Task

class TaskDataSourceImpl(
    private val taskApi: TaskApi
) : TaskDataSource {

    override suspend fun getTaskList(): List<Task> {
        return taskApi.getTaskList().map { it.toDomain() }
    }
}