package com.template.technocratia.domain.repository

import com.template.technocratia.domain.entities.UserDB

interface UserRepositoryDB {
    suspend fun getUserFromDB(): List<UserDB>

    suspend fun saveUserToDB(user: UserDB)
}