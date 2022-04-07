package com.template.technocratia.domain.usecase

import com.template.technocratia.domain.entities.User
import com.template.technocratia.domain.repository.ProfileRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val repository: ProfileRepository) {

    suspend fun execute(): User{
        val profile = repository.getProfileFromServer().results[0]
        return User(
            photo = profile.picture.medium,
            fullName = profile.name.toString(),
            dateOfBirth = profile.dob.toString(),
            phoneNumber = profile.phone,
            location = profile.location.toString(),
            coordinates = profile.location.coordinates.toString()
        )
    }
}