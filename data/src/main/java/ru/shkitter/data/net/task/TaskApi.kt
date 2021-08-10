package ru.shkitter.data.net.task

import retrofit2.http.GET
import ru.shkitter.data.net.task.model.TaskDto

interface TaskApi {

    @GET("tasks")
    suspend fun getTaskList(): List<TaskDto>
}