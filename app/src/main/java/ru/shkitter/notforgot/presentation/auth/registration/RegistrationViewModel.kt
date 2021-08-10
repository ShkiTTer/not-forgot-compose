package ru.shkitter.notforgot.presentation.auth.registration

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import ru.shkitter.domain.auth.RegistrationUseCase
import ru.shkitter.domain.auth.model.RegistrationParams
import ru.shkitter.domain.validation.ValidationRule
import ru.shkitter.domain.validation.ValidationUseCase
import ru.shkitter.notforgot.R
import ru.shkitter.notforgot.presentation.auth.registration.model.RegistrationAction
import ru.shkitter.notforgot.presentation.common.extensions.asLiveData
import ru.shkitter.notforgot.presentation.common.extensions.postEvent
import ru.shkitter.notforgot.presentation.common.state.ContentState
import ru.shkitter.notforgot.presentation.common.state.StateViewModel

class RegistrationViewModel(
    inputEmail: String,
    private val registrationUseCase: RegistrationUseCase,
    private val validationUseCase: ValidationUseCase
) : StateViewModel<RegistrationAction>() {

    private val _email = MutableLiveData(inputEmail)
    val email = _email.asLiveData()

    private val _name = MutableLiveData<String>()
    val name = _name.asLiveData()

    private val _password = MutableLiveData<String>()
    val password = _password.asLiveData()

    private val _repeatPassword = MutableLiveData<String>()
    val repeatPassword = _repeatPassword.asLiveData()

    private val _emailError = MutableLiveData<Int?>()
    val emailError = _emailError.asLiveData()

    private val _nameError = MutableLiveData<Int?>()
    val nameError = _nameError.asLiveData()

    private val _passwordError = MutableLiveData<Int?>()
    val passwordError = _passwordError.asLiveData()

    private val _repeatPasswordError = MutableLiveData<Int?>()
    val repeatPasswordError = _repeatPasswordError.asLiveData()

    private suspend fun validateEmail(email: String): Boolean {
        val error = when {
            email.isEmpty() -> R.string.error_empty
            !validationUseCase(ValidationRule.Email(email)) -> R.string.error_email_incorrect
            else -> null
        }

        _emailError.value = error
        return error == null
    }

    private fun validateName(name: String): Boolean {
        val error = when {
            name.isEmpty() -> R.string.error_empty
            else -> null
        }

        _nameError.value = error
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

    private fun validateRepeatPassword(password: String, repeatPassword: String): Boolean {
        val error = when {
            repeatPassword.isEmpty() -> R.string.error_empty
            password != repeatPassword -> R.string.error_password_not_equal
            else -> null
        }

        _repeatPasswordError.value = error
        return error == null
    }

    private suspend fun validateFields(
        email: String,
        name: String,
        password: String,
        repeatPassword: String
    ): Boolean = validateEmail(email)
            && validateName(name)
            && validatePassword(password)
            && validateRepeatPassword(password, repeatPassword)

    fun onEmailChanged(value: String) {
        _email.value = value
        _emailError.value = null
    }

    fun onNameChanged(value: String) {
        _name.value = value
        _nameError.value = null
    }

    fun onPasswordChanged(value: String) {
        _password.value = value
        _passwordError.value = null
    }

    fun onRepeatPasswordChanged(value: String) {
        _repeatPassword.value = value
        _repeatPasswordError.value = null
    }

    fun register() {
        viewModelScope.launch {
            val localEmail = email.value.orEmpty()
            val localName = name.value.orEmpty()
            val localPassword = password.value.orEmpty()
            val localRepeatPassword = repeatPassword.value.orEmpty()

            if (!validateFields(
                    localEmail,
                    localName,
                    localPassword,
                    localRepeatPassword
                )
            ) return@launch

            val param = RegistrationParams(localEmail, localPassword, localName)
            registrationUseCase(param)
                .onStart { _contentState.value = ContentState.Loading }
                .onCompletion { _contentState.value = ContentState.Content }
                .collect { result ->
                    result
                        .onSuccess {
                            _event.postEvent(RegistrationAction.Succeeded)
                        }
                        .onFailure {
                            _event.postEvent(RegistrationAction.Error(it))
                        }
                }
        }
    }
}