package com.template.technocratia.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.template.technocratia.domain.entities.User
import com.template.technocratia.domain.usecase.GetUserFromServerUseCase
import com.template.technocratia.domain.usecase.SaveUserToDatabaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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
            loadingState.postValue(true)
            val user = getUserFromServerUseCase.getUserFromServer()
            loadingState.postValue(false)
            savedState.postValue(false)
            users.postValue(user)
        }
    }

    fun saveUser() {
        viewModelScope.launch {
            val user = users.value
            if (user != null) {
                savedState.postValue(true)
                saveUserToDatabaseUseCase.saveUserToDatabase(user)
            }
        }
    }

}