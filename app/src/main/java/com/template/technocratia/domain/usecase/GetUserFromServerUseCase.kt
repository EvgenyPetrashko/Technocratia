package com.template.technocratia.domain.usecase

import com.template.technocratia.data.network.entities.Result
import com.template.technocratia.domain.entities.User
import com.template.technocratia.domain.repository.ProfileRepository
import javax.inject.Inject

class GetUserFromServerUseCase @Inject constructor(private val repository: ProfileRepository) {

    suspend fun getUserFromServer(): User {
        return repository.getProfileFromServer().results[0].toUser()
    }

    private fun fromIsoToNormDate(isoDate: String): String {
        val parts = isoDate.split("-", "T")
        return "${parts[2]}.${parts[1]}.${parts[0]}"
    }

    private fun Result.toUser(): User = User(
        photo = this.picture.large,
        fullName = this.name.toString(),
        dateOfBirth = fromIsoToNormDate(this.dob.toString()),
        phoneNumber = this.phone,
        location = this.location.toString(),
        coordinates = this.location.coordinates.toString()
    )
}