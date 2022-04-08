package com.template.technocratia.domain.usecase

import com.template.technocratia.domain.entities.User
import com.template.technocratia.domain.entities.UserDB
import com.template.technocratia.domain.repository.UserRepositoryDB
import javax.inject.Inject

class GetUsersFromDatabaseUseCase @Inject constructor(private val userRepositoryDb: UserRepositoryDB) {

    fun getUsersFromDB(): List<User> {
        return userRepositoryDb.getUserFromDB().map {
            it.toUser()
        }
    }

    private fun UserDB.toUser() = User(
        photo = photo,
        fullName = fullName,
        dateOfBirth = dateOfBirth,
        phoneNumber = phoneNumber,
        location = location,
        coordinates = coordinates,
    )
}