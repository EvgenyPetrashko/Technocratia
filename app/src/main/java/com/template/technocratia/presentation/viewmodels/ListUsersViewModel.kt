package com.template.technocratia.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.template.technocratia.domain.entities.User
import com.template.technocratia.domain.usecase.GetUsersFromDatabaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ListUsersViewModel @Inject constructor(private val getUsersFromDatabaseUseCase: GetUsersFromDatabaseUseCase) :
    ViewModel() {
    val users = MutableLiveData<List<User>>()

    fun getUsers() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                users.postValue(getUsersFromDatabaseUseCase.getUsersFromDB())
            }
        }
    }

}