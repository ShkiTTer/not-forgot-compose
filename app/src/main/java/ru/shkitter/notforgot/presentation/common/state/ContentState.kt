package ru.shkitter.notforgot.presentation.common.state

sealed interface ContentState {
    object Loading : ContentState
    object Content : ContentState
    object Empty : ContentState
    data class Error(val throwable: Throwable) : ContentState
}