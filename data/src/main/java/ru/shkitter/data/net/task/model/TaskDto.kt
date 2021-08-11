package ru.shkitter.data.net.task.model

import kotlinx.serialization.Serializable
import ru.shkitter.domain.task.model.Task
import java.time.Instant
import java.time.ZoneId

@Serializable
data class TaskDto(
    val id: Int,
    val title: String,
    val description: String,
    val done: Int,
    val deadline: Long,
    val created: Long,
    val category: CategoryDto,
    val priority: PriorityDto
) {

    fun toDomain() = Task(
        id = id,
        title = title,
        description = description,
        done = done == 1,
        deadline = Instant.ofEpochSecond(deadline),
        created = Instant.ofEpochSecond(created),
        category = category.toDomain(),
        priority = priority.toDomain()
    )
}
