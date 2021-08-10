package ru.shkitter.data.db.session

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.shkitter.data.db.session.model.SessionEntity

@Dao
interface SessionDao {

    @Query("SELECT * FROM sessions LIMIT 1")
    fun observeSession(): Flow<SessionEntity?>

    @Query("SELECT * FROM sessions LIMIT 1")
    suspend fun getSessionEager(): SessionEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSession(sessionEntity: SessionEntity)

    @Query("DELETE FROM sessions")
    suspend fun clearSessions()
}