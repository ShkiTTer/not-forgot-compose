package ru.shkitter.notforgot.presentation.auth.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import ru.shkitter.domain.auth.LoginUseCase
import ru.shkitter.domain.auth.model.LoginParams
import ru.shkitter.notforgot.presentation.auth.login.model.LoginAction
import ru.shkitter.notforgot.presentation.auth.login.model.LoginState
import ru.shkitter.notforgot.presentation.common.ViewEvent
import ru.shkitter.notforgot.presentation.common.extensions.asLiveData
import ru.shkitter.notforgot.presentation.common.extensions.postEvent
import ru.shkitter.notforgot.presentation.common.state.ContentState
import ru.shkitter.notforgot.presentation.common.state.StateViewModel

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : StateViewModel<LoginAction>() {
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

    fun login() {
        viewModelScope.launch {
            val localEmail = email.value ?: return@launch
            val localPassword = password.value ?: return@launch
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