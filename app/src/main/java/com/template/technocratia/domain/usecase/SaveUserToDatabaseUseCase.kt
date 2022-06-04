package com.template.technocratia.domain.usecase

import com.template.technocratia.domain.entities.User
import com.template.technocratia.domain.entities.UserDB
import com.template.technocratia.domain.repository.UserRepositoryDB
import javax.inject.Inject

class SaveUserToDatabaseUseCase @Inject constructor(private val userRepositoryDB: UserRepositoryDB) {

    suspend fun saveUserToDatabase(user: User) {
        userRepositoryDB.saveUserToDB(user.toUserDB())
    }

    private fun User.toUserDB() = UserDB(
        photo = photo,
        fullName = fullName,
        dateOfBirth = dateOfBirth,
        phoneNumber = phoneNumber,
        location = location,
        coordinates = coordinates,
    )
}