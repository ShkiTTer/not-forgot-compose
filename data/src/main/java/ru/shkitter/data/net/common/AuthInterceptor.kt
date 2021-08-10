package ru.shkitter.data.net.common

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import ru.shkitter.domain.session.GetCurrentTokenUseCase

class AuthInterceptor(
    private val getCurrentTokenUseCase: GetCurrentTokenUseCase
) : Interceptor {

    companion object {
        private const val HEADER_ACCEPT_NAME = "Accept"
        private const val HEADER_ACCEPT_VALUE = "application/json"
        private const val HEADER_CONTENT_TYPE_NAME = "Content-Type"
        private const val HEADER_CONTENT_TYPE_VALUE = "application/x-www-form-urlencoded"
        private const val HEADER_AUTHORIZATION_NAME = "Authorization"
        private const val BEARER = "Bearer"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking { getCurrentTokenUseCase() }

        val request = chain.request()
            .newBuilder().apply {
                addHeader(HEADER_ACCEPT_NAME, HEADER_ACCEPT_VALUE)
                addHeader(HEADER_CONTENT_TYPE_NAME, HEADER_CONTENT_TYPE_VALUE)
                token?.let {
                    addHeader(HEADER_AUTHORIZATION_NAME, "$BEARER $token")
                }
            }.build()

        return chain.proceed(request)
    }
}