package com.template.technocratia.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.template.technocratia.domain.entities.UserDB

@Dao
interface UserDao {
    @Query("SELECT * FROM UserDB")
    fun getAll(): List<UserDB>

    @Insert
    fun insert(user: UserDB)
}