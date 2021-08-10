package ru.shkitter.data.net.task.model

import kotlinx.serialization.Serializable
import ru.shkitter.domain.task.model.Priority

@Serializable
data class PriorityDto(
    val id: Int,
    val name: String,
    val color: String
) {

    fun toDomain() = Priority(
        id = id,
        name = name,
        color = color
    )
}
