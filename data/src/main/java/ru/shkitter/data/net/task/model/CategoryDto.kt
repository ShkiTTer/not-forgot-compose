package ru.shkitter.data.net.task.model

import kotlinx.serialization.Serializable
import ru.shkitter.domain.task.model.Category

@Serializable
data class CategoryDto(
    val id: Int,
    val name: String
) {

    fun toDomain() = Category(
        id = id,
        name = name
    )
}
