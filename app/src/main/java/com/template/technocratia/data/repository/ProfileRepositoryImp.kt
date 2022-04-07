package com.template.technocratia.data.repository

import com.template.technocratia.data.network.API
import com.template.technocratia.data.network.entities.Profile
import com.template.technocratia.domain.repository.ProfileRepository
import javax.inject.Inject


class ProfileRepositoryImp @Inject constructor(private val api: API): ProfileRepository{
    override suspend fun getProfileFromServer(): Profile {
        return api.getProfile()
    }

}