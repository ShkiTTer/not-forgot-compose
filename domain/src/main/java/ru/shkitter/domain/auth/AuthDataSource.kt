package ru.shkitter.domain.auth

import ru.shkitter.domain.auth.model.ApiToken

interface AuthDataSource {
    suspend fun registration(email: String, password: String, name: String): ApiToken
    suspend fun login(email: String, password: String): ApiToken
}