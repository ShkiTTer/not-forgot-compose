package ru.shkitter.notforgot.presentation.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.shkitter.notforgot.presentation.common.extensions.asLiveData

class RegistrationViewModel(inputEmail: String) : ViewModel() {
    private val _email = MutableLiveData(inputEmail)
    val email = _email.asLiveData()

    private val _name = MutableLiveData<String>()
    val name = _name.asLiveData()

    private val _password = MutableLiveData<String>()
    val password = _password.asLiveData()

    private val _repeatPassword = MutableLiveData<String>()
    val repeatPassword = _repeatPassword.asLiveData()

    fun onEmailChanged(value: String) {
        _email.value = value
    }

    fun onNameChanged(value: String) {
        _name.value = value
    }

    fun onPasswordChanged(value: String) {
        _password.value = value
    }

    fun onRepeatPasswordChanged(value: String) {
        _repeatPassword.value = value
    }
}