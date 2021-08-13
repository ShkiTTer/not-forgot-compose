package ru.shkitter.data.net.task.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TaskBody(
    val title: String,
    val description: String,
    val done: Int,
    val deadline: Long,
    @SerialName("category_id")
    val categoryId: Int,
    @SerialName("priority_id")
    val priorityId: Int
)