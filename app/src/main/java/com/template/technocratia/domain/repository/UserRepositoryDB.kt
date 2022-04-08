package com.template.technocratia.domain.repository

import com.template.technocratia.domain.entities.UserDB

interface UserRepositoryDB {
    fun getUserFromDB(): List<UserDB>

    fun saveUserToDB(user: UserDB)
}