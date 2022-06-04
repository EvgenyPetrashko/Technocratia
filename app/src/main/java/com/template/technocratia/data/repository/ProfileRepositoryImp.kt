package com.template.technocratia.data.repository

import android.net.Network
import com.template.technocratia.data.network.API
import com.template.technocratia.data.network.entities.Profile
import com.template.technocratia.domain.repository.ProfileRepository
import com.template.technocratia.domain.utils.ErrorHandler
import com.template.technocratia.domain.utils.NetworkResponse
import javax.inject.Inject


class ProfileRepositoryImp @Inject constructor(private val api: API, private val errorHandler: ErrorHandler) : ProfileRepository {
    override suspend fun getProfileFromServer(): NetworkResponse<Profile> {
        return try {
            NetworkResponse.Success(api.getProfile())
        } catch (t: Throwable){
            NetworkResponse.Failure(errorHandler.getError(t))
        }
    }
}