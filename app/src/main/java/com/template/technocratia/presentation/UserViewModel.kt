package com.template.technocratia.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.template.technocratia.domain.entities.User
import com.template.technocratia.domain.usecase.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {
    val users = MutableLiveData<User>()
    val errors = MutableLiveData<String>()
    val loadingState = MutableLiveData(false)

    fun refreshUser() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                loadingState.postValue(true)
                val observable = getUserUseCase.execute()
                observable.doOnError {
                    errors.postValue("Error")
                }.subscribe {
                    users.postValue(it)
                    loadingState.postValue(false)
                }
            }
        }
    }

}