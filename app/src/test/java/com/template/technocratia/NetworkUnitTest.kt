package com.template.technocratia

import com.template.technocratia.data.network.NetworkModule
import com.template.technocratia.data.repository.ProfileRepositoryImp
import com.template.technocratia.domain.entities.User
import com.template.technocratia.domain.usecase.GetUserFromServerUseCase
import io.reactivex.observers.TestObserver
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class NetworkUnitTest {

    private lateinit var getUserFromServerUseCase: GetUserFromServerUseCase


    @Before
    fun create() {
        val networkModule = NetworkModule()
        val clientInst = networkModule.provideClient()
        val retrofitInst = networkModule.provideRetrofitInstance(clientInst)
        val apiInst = networkModule.provideApi(retrofitInst)
        val profileRepositoryImp = ProfileRepositoryImp(apiInst)
        getUserFromServerUseCase = GetUserFromServerUseCase(profileRepositoryImp)
    }

    @Test
    fun test() {
        val testObservable = getUserFromServerUseCase.getUserFromServer()
        val testObserver = TestObserver<User>()
        testObservable.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        val user = testObserver.values()[0]
        assertNotNull(user.photo)
        assertNotNull(user.phoneNumber)
        assertNotNull(user.coordinates)
        assertNotNull(user.dateOfBirth)
        assertNotNull(user.fullName)
        assertNotNull(user.location)
    }


}