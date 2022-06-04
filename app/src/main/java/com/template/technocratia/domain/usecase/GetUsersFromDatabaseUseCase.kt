package com.template.technocratia.domain.usecase

import com.template.technocratia.domain.entities.UserStored
import com.template.technocratia.domain.repository.UserRepositoryDB
import javax.inject.Inject

class GetUsersFromDatabaseUseCase @Inject constructor(private val userRepositoryDb: UserRepositoryDB) {

    suspend fun getUsersFromDB(): List<UserStored> {
        return userRepositoryDb.getUserFromDB()
    }
}