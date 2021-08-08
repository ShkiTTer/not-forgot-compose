package ru.shkitter.notforgot.presentation.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.shkitter.notforgot.presentation.common.extensions.asLiveData

class LoginViewModel : ViewModel() {
    private val _email = MutableLiveData<String>()
    val email = _email.asLiveData()

    private val _password = MutableLiveData<String>()
    val password = _password.asLiveData()

    fun onEmailChanged(value: String) {
        _email.value = value
    }

    fun onPasswordChanged(value: String) {
        _password.value = value
    }
}