package com.template.technocratia.domain.repository

import com.template.technocratia.data.network.entities.Profile
import io.reactivex.Observable

interface ProfileRepository {
    fun getProfileFromServer(): Observable<Profile>
}