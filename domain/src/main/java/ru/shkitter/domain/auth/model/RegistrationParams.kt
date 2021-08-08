package ru.shkitter.domain.auth.model

data class RegistrationParams(
    val email: String,
    val password: String,
    val name: String
)
