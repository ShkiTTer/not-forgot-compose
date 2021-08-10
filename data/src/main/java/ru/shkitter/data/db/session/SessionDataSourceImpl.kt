package ru.shkitter.data.db.session

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.shkitter.data.db.session.model.SessionEntity
import ru.shkitter.domain.session.SessionDataSource
import ru.shkitter.domain.session.model.Session

class SessionDataSourceImpl(
    private val sessionDao: SessionDao
) : SessionDataSource {

    override fun observeSession(): Flow<Session?> =
        sessionDao.observeSession().map { it?.toDomain() }

    override suspend fun saveSession(session: Session) {
        sessionDao.saveSession(SessionEntity.fromDomain(session))
    }

    override suspend fun clearSessions() {
        sessionDao.clearSessions()
    }
}