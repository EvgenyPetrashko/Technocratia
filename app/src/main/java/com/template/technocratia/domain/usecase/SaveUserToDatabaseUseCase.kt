package com.template.technocratia.domain.usecase

import com.template.technocratia.domain.entities.User
import com.template.technocratia.domain.entities.UserStored
import com.template.technocratia.domain.repository.UserRepositoryDB
import javax.inject.Inject

class SaveUserToDatabaseUseCase @Inject constructor(private val userRepositoryDB: UserRepositoryDB) {

    suspend fun saveUserToDatabase(user: User) {
        userRepositoryDB.saveUserToDB(user.toUserStored())
    }

    private fun User.toUserStored() = UserStored(
        photo = photo,
        fullName = fullName,
    )
}