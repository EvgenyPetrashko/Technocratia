package com.template.technocratia.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.template.technocratia.domain.entities.UserDB

@Database(entities = [UserDB::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}