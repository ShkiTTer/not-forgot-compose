package ru.shkitter.notforgot.presentation.auth.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import ru.shkitter.domain.auth.LoginUseCase
import ru.shkitter.domain.auth.model.LoginParams
import ru.shkitter.domain.validation.ValidationRule
import ru.shkitter.domain.validation.ValidationUseCase
import ru.shkitter.notforgot.R
import ru.shkitter.notforgot.presentation.auth.login.model.LoginAction
import ru.shkitter.notforgot.presentation.common.extensions.asLiveData
import ru.shkitter.notforgot.presentation.common.extensions.postEvent
import ru.shkitter.notforgot.presentation.common.state.ContentState
import ru.shkitter.notforgot.presentation.common.state.StateViewModel

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val validationUseCase: ValidationUseCase
) : StateViewModel<LoginAction>() {
    private val _email = MutableLiveData<String>()
    val email = _email.asLiveData()

    private val _password = MutableLiveData<String>()
    val password = _password.asLiveData()

    private val _emailError = MutableLiveData<Int?>()
    val emailError = _emailError.asLiveData()

    private val _passwordError = MutableLiveData<Int?>()
    val passwordError = _passwordError.asLiveData()

    private suspend fun validateEmail(email: String): Boolean {
        val error = when {
            email.isEmpty() -> R.string.error_empty
            !validationUseCase(ValidationRule.Email(email)) -> R.string.error_email_incorrect
            else -> null
        }

        _emailError.value = error
        return error == null
    }

    private suspend fun validatePassword(password: String): Boolean {
        val error = when {
            password.isEmpty() -> R.string.error_empty
            !validationUseCase(
                ValidationRule.MinLength(
                    password,
                    ValidationRule.MinLength.PASSWORD_MIN_LENGTH
                )
            ) -> R.string.error_password_min_length
            else -> null
        }

        _passwordError.value = error
        return error == null
    }

    fun onEmailChanged(value: String) {
        _email.value = value
        _emailError.value = null
    }

    fun onPasswordChanged(value: String) {
        _password.value = value
        _passwordError.value = null
    }

    fun login() {
        viewModelScope.launch {
            val localEmail = email.value.orEmpty()
            val localPassword = password.value.orEmpty()

            val emailValid = validateEmail(localEmail)
            val passwordValid = validatePassword(localPassword)

            if (!emailValid || !passwordValid) return@launch

            val params = LoginParams(localEmail, localPassword)

            loginUseCase(params)
                .onStart { _contentState.value = ContentState.Loading }
                .onCompletion { _contentState.value = ContentState.Content }
                .collect { result ->
                    result
                        .onSuccess {
                            _event.postEvent(LoginAction.Succeeded)
                        }
                        .onFailure {
                            _event.postEvent(LoginAction.Error(it))
                        }
                }
        }
    }
}