package ru.shkitter.data.db.common

import android.content.Context
import androidx.room.Room

object Database {
    fun build(context: Context) = Room
        .databaseBuilder(context, AppDatabase::class.java, "app_database")
        .build()
}