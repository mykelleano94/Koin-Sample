package com.neds.koinsample.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    protected val _loading = MediatorLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    protected val _errorMessage = MediatorLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage
}