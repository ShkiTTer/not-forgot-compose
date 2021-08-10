package ru.shkitter.data.db.common

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.shkitter.data.db.session.SessionDao
import ru.shkitter.data.db.session.model.SessionEntity

@Database(entities = [SessionEntity::class], exportSchema = true, version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getSessionDao(): SessionDao
}