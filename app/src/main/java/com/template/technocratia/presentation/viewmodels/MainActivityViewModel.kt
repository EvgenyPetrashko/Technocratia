package com.template.technocratia.presentation.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
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
    private val usersMutable = MutableLiveData<User>()
    private val errorsMutable = MutableLiveData<String>()
    private val loadingStateMutable = MutableLiveData(false)
    private val savedStateMutable = MutableLiveData(false)

    val users: LiveData<User>
        get() = usersMutable

    val errors: LiveData<String>
        get() = errorsMutable

    val loadingState: LiveData<Boolean>
        get() = loadingStateMutable

    val savedState: LiveData<Boolean>
        get() = savedStateMutable

    @SuppressLint("NullSafeMutableLiveData")
    fun refreshUser() {
        viewModelScope.launch {
            loadingStateMutable.postValue(true)
            savedStateMutable.postValue(true)
            val userWrapper = getUserFromServerUseCase.getUserFromServer()
            val incomingError = userWrapper.error
            if (incomingError != null) {
                errorsMutable.value = incomingError
            } else {
                usersMutable.postValue(userWrapper.data)
                savedStateMutable.postValue(false)
            }
            loadingStateMutable.postValue(false)
        }
    }

    fun saveUser() {
        viewModelScope.launch {
            val user = users.value
            if (user != null) {
                savedStateMutable.postValue(true)
                saveUserToDatabaseUseCase.saveUserToDatabase(user)
            }
        }
    }

}