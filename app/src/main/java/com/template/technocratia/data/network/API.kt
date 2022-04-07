package com.template.technocratia.data.network

import com.template.technocratia.data.network.entities.Profile
import retrofit2.http.GET

interface API {
    @GET(".")
    suspend fun getProfile() : Profile
}