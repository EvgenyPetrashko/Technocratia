package com.template.technocratia.domain.repository

import android.net.Network
import com.template.technocratia.data.network.entities.Profile
import com.template.technocratia.domain.utils.NetworkResponse

interface ProfileRepository {
    suspend fun getProfileFromServer(): NetworkResponse<Profile>
}