package ru.shkitter.data.net.auth.model

import kotlinx.serialization.Serializable

@Serializable
data class RegistrationBody(
    val email: String,
    val name: String,
    val password: String
)
