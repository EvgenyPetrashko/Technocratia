package com.template.technocratia.domain.repository

import com.template.technocratia.data.network.entities.Profile

interface ProfileRepository {
    suspend fun getProfileFromServer(): Profile
}