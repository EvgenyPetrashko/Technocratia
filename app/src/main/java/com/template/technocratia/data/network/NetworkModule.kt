package com.template.technocratia.data.network

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    private val baseUrl = "https://randomuser.me/api/"

    @Singleton
    @Provides
    fun provideRetrofitInstance(client: OkHttpClient): Retrofit {

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            //.addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideClient(): OkHttpClient{
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.addInterceptor { chain ->
            val request = chain.request()
            val response = chain.proceed(request)
            Log.d("ServerResponse", response.peekBody(2048).string() ?:"Null");
            response
        }
        return clientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): API = retrofit.create(API::class.java)
}