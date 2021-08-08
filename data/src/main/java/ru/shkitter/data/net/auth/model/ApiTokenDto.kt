package ru.shkitter.data.net.auth.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.shkitter.domain.auth.model.ApiToken

@Serializable
data class ApiTokenDto(
    @SerialName("api_token")
    val apiToken: String
) {

    fun toDomain() = ApiToken(
        apiToken = apiToken
    )
}
