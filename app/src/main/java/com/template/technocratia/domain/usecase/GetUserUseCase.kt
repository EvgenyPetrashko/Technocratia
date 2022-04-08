package com.template.technocratia.domain.usecase

import com.template.technocratia.domain.entities.User
import com.template.technocratia.domain.repository.ProfileRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val repository: ProfileRepository) {

    fun execute(): Observable<User> {
        return repository.getProfileFromServer().flatMap {
            val profile = it.results[0]
            Observable.just(
                User(
                    photo = profile.picture.large,
                    fullName = profile.name.toString(),
                    dateOfBirth = fromIsoToNormDate(profile.dob.toString()),
                    phoneNumber = profile.phone,
                    location = profile.location.toString(),
                    coordinates = profile.location.coordinates.toString()
                )
            )
        }

    }

    private fun fromIsoToNormDate(isoDate: String): String {
        val parts = isoDate.split("-", "T")
        return "${parts[2]}.${parts[1]}.${parts[0]}"
    }
}