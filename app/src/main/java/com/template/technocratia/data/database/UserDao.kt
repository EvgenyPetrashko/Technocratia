package com.template.technocratia.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.template.technocratia.domain.entities.UserDB

@Dao
interface UserDao {
    @Query("SELECT * FROM UserDB")
    suspend fun getAll(): List<UserDB>

    @Insert
    suspend fun insert(user: UserDB)
}