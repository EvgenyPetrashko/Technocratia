package com.template.technocratia.data.repository

import com.template.technocratia.data.database.UserDao
import com.template.technocratia.domain.entities.UserDB
import com.template.technocratia.domain.repository.UserRepositoryDB
import javax.inject.Inject

class UserRepositoryDbImp @Inject constructor(private val userDao: UserDao) : UserRepositoryDB {
    override fun getUserFromDB(): List<UserDB> {
        return userDao.getAll()
    }

    override fun saveUserToDB(user: UserDB) {
        userDao.insert(user)
    }

}