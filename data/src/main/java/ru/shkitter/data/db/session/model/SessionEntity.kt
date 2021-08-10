package ru.shkitter.data.db.session.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.shkitter.domain.session.model.Session

@Entity(tableName = "sessions")
data class SessionEntity(
    @PrimaryKey
    val email: String,
    val apiToken: String
) {

    companion object {
        fun fromDomain(data: Session) = SessionEntity(
            email = data.email,
            apiToken = data.apiToken
        )
    }

    fun toDomain() = Session(
        email = email,
        apiToken = apiToken
    )
}
