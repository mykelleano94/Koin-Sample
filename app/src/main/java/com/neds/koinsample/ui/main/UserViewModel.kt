package com.neds.koinsample.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.neds.koinsample.data.model.BaseViewModel
import com.neds.koinsample.data.model.User
import com.neds.koinsample.data.repository.UserRepository
import com.neds.koinsample.util.NetworkUtil
import kotlinx.coroutines.launch

class UserViewModel(
    private val userRepository: UserRepository,
    private val networkHelper: NetworkUtil
) : BaseViewModel() {
    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            _loading.postValue(true)
            if (networkHelper.isNetworkConnected()) {
                userRepository.getUsers().let { response ->
                    response.body()?.let { users ->
                        _users.postValue(users)
                    } ?: _errorMessage.postValue(response.errorBody().toString())
                    _loading.postValue(false)
                }
            } else _errorMessage.postValue("No Internet Connection")
        }
    }
}