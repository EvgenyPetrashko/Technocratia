package com.template.technocratia.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkModule {
    private val baseUrl = "https://randomuser.me/api/"

    fun provideRetrofitInstance(): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        //.addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    fun provideApi(retrofit: Retrofit): API = retrofit.create(API::class.java)
}