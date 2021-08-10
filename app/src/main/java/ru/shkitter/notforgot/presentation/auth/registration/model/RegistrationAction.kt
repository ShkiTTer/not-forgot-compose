package ru.shkitter.notforgot.presentation.auth.registration.model

sealed interface RegistrationAction {
    object Succeeded : RegistrationAction
    data class Error(val throwable: Throwable) : RegistrationAction
}