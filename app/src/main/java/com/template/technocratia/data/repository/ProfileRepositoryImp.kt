package com.template.technocratia.data.repository

import com.template.technocratia.data.network.NetworkModule
import com.template.technocratia.data.network.entities.Profile
import com.template.technocratia.domain.repository.ProfileRepository

class ProfileRepositoryImp(val networkModule: NetworkModule): ProfileRepository{
    override suspend fun getProfileFromServer(): Profile {
        return networkModule.provideApi(networkModule.provideRetrofitInstance()).getProfile()
    }

}