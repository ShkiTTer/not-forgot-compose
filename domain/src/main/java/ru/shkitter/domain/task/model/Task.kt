package ru.shkitter.domain.task.model

import java.time.Instant

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val done: Boolean,
    val deadline: Instant,
    val created: Instant,
    val category: Category,
    val priority: Priority
)
