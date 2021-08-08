package ru.shkitter.data.net.auth

import ru.shkitter.data.net.auth.model.LoginBody
import ru.shkitter.data.net.auth.model.RegistrationBody
import ru.shkitter.domain.auth.AuthDataSource
import ru.shkitter.domain.auth.model.ApiToken

class AuthDataSourceImpl(private val authApi: AuthApi) : AuthDataSource {

    override suspend fun login(email: String, password: String): ApiToken {
        return authApi.login(LoginBody(email, password)).toDomain()
    }

    override suspend fun registration(email: String, password: String, name: String): ApiToken {
        return authApi.registration(RegistrationBody(email, name, password)).toDomain()
    }
}