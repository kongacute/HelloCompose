package com.binh.hellocompose

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel() : ViewModel() {
    private val _clickedTimes = MutableLiveData<Int>()
    val clickedTimes: LiveData<Int>
        get() = _clickedTimes

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String>
        get() = _userName

    private val _password = MutableLiveData<String>()
    val password: LiveData<String>
        get() = _password

    init {
        _clickedTimes.value = 0
        _userName.value = ""
        _password.value = ""
    }

    fun increaseClickedTimes() {
        _clickedTimes.value?.let {
            _clickedTimes.value = it + 1
        }
    }

    fun setUserName(userName: String) {
        _userName.value = userName
    }

    fun setPassword(password: String) {
        _password.value = password
    }
}