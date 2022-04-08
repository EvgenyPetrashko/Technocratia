package com.template.technocratia.data.network

import com.template.technocratia.data.network.entities.Profile
import io.reactivex.Observable
import retrofit2.http.GET

interface API {
    @GET(".")
    fun getProfile(): Observable<Profile>
}