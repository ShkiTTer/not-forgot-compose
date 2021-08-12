package ru.shkitter.data.net.task

import retrofit2.http.GET
import ru.shkitter.data.net.task.model.CategoryDto
import ru.shkitter.data.net.task.model.PriorityDto
import ru.shkitter.data.net.task.model.TaskDto

interface TaskApi {

    @GET("tasks")
    suspend fun getTaskList(): List<TaskDto>

    @GET("categories")
    suspend fun getCategories(): List<CategoryDto>

    @GET("priorities")
    suspend fun getPriorities(): List<PriorityDto>
}