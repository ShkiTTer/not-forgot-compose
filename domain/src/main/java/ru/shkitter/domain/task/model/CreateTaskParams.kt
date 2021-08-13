package ru.shkitter.domain.task.model

import java.time.Instant

data class CreateTaskParams(
    val title: String,
    val description: String,
    val done: Boolean,
    val deadline: Instant,
    val categoryId: Int,
    val priorityId: Int
)