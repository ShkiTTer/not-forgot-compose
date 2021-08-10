package ru.shkitter.domain.session

import kotlinx.coroutines.flow.Flow
import ru.shkitter.domain.session.model.Session

interface SessionDataSource {
    fun observeSession(): Flow<Session?>
    suspend fun saveSession(session: Session)
    suspend fun clearSessions()
}