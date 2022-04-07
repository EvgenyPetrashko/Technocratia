package com.template.technocratia

import com.template.technocratia.data.network.NetworkModule
import com.template.technocratia.data.repository.ProfileRepositoryImp
import com.template.technocratia.domain.usecase.GetUserUseCase
import org.junit.Test

import org.junit.Assert.*
import kotlinx.coroutines.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun network_call(): Unit = runBlocking{
        val module = NetworkModule()
        val api = module.provideApi(module.provideRetrofitInstance())
        val profile = api.getProfile()
        print(profile)
    }

    @Test
    fun check_network_architecture(): Unit = runBlocking {
        val user = GetUserUseCase(ProfileRepositoryImp(NetworkModule())).execute()
        print(user.photo)
    }
}