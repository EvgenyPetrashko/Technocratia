package com.template.technocratia.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.template.technocratia.domain.entities.UserStored

@Database(entities = [UserStored::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}