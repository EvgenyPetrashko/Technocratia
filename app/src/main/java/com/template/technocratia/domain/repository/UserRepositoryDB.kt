package com.template.technocratia.domain.repository

import com.template.technocratia.domain.entities.UserStored

interface UserRepositoryDB {
    suspend fun getUserFromDB(): List<UserStored>

    suspend fun saveUserToDB(user: UserStored)
}