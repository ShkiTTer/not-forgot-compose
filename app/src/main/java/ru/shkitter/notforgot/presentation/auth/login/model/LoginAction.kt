package ru.shkitter.notforgot.presentation.auth.login.model

sealed interface LoginAction {
    object Succeeded: LoginAction
    data class Error(val throwable: Throwable): LoginAction
}