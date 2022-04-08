package com.template.technocratia.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.template.technocratia.domain.entities.User
import com.template.technocratia.domain.usecase.GetUserFromServerUseCase
import com.template.technocratia.domain.usecase.SaveUserToDatabaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getUserFromServerUseCase: GetUserFromServerUseCase,
    private val saveUserToDatabaseUseCase: SaveUserToDatabaseUseCase
) : ViewModel() {
    val users = MutableLiveData<User>()
    val errors = MutableLiveData<String>()
    val loadingState = MutableLiveData(false)
    val savedState = MutableLiveData(false)

    fun refreshUser() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                loadingState.postValue(true)
                val observable = getUserFromServerUseCase.getUserFromServer()
                observable.doOnTerminate {
                    loadingState.postValue(false)
                    savedState.postValue(false)
                }.subscribe(
                    { users.postValue(it) },
                    { errors.postValue("Error! Please try Again Later") }
                )
            }
        }
    }

    fun saveUser() {
        viewModelScope.launch {
            val user = users.value
            if (user != null) {
                withContext(Dispatchers.IO) {
                    savedState.postValue(true)
                    saveUserToDatabaseUseCase.saveUserToDatabase(user)
                }
            }
        }
    }

}