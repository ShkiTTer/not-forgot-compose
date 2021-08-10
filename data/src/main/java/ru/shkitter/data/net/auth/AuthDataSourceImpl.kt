package ru.shkitter.data.net.auth

import ru.shkitter.data.db.session.SessionDao
import ru.shkitter.data.db.session.model.SessionEntity
import ru.shkitter.data.net.auth.model.LoginBody
import ru.shkitter.data.net.auth.model.RegistrationBody
import ru.shkitter.domain.auth.AuthDataSource
import ru.shkitter.domain.auth.model.ApiToken

class AuthDataSourceImpl(
    private val authApi: AuthApi,
    private val sessionDao: SessionDao
) : AuthDataSource {

    override suspend fun login(email: String, password: String): ApiToken {
        return authApi.login(LoginBody(email, password)).toDomain().also {
            sessionDao.saveSession(
                SessionEntity(email, it.apiToken)
            )
        }
    }

    override suspend fun registration(email: String, password: String, name: String): ApiToken {
        return authApi.registration(RegistrationBody(email, name, password)).toDomain().also {
            sessionDao.saveSession(
                SessionEntity(email, it.apiToken)
            )
        }
    }
}