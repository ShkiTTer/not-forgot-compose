package ru.shkitter.data.net.auth

import retrofit2.http.Body
import retrofit2.http.POST
import ru.shkitter.data.net.auth.model.ApiTokenDto
import ru.shkitter.data.net.auth.model.LoginBody
import ru.shkitter.data.net.auth.model.RegistrationBody

interface AuthApi {
    @POST("register")
    suspend fun registration(@Body body: RegistrationBody): ApiTokenDto

    @POST("login")
    suspend fun login(@Body body: LoginBody): ApiTokenDto
}