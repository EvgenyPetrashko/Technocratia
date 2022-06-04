package com.template.technocratia.presentation.viewmodels

import androidx.lifecycle.LiveData
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
    private val usersMutable = MutableLiveData<List<User>>()

    val users: LiveData<List<User>>
        get() = usersMutable

    fun getUsers() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                usersMutable.postValue(getUsersFromDatabaseUseCase.getUsersFromDB())
            }
        }
    }

}