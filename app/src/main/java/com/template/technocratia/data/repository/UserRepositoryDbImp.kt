package com.template.technocratia.data.repository

import com.template.technocratia.data.database.UserDao
import com.template.technocratia.domain.entities.UserStored
import com.template.technocratia.domain.repository.UserRepositoryDB
import javax.inject.Inject

class UserRepositoryDbImp @Inject constructor(private val userDao: UserDao) : UserRepositoryDB {
    override suspend fun getUserFromDB(): List<UserStored> {
        return userDao.getAll()
    }

    override suspend fun saveUserToDB(user: UserStored) {
        userDao.insert(user)
    }

}