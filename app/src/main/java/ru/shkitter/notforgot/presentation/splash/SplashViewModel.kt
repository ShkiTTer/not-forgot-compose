package ru.shkitter.notforgot.presentation.splash

import androidx.lifecycle.ViewModel
import ru.shkitter.domain.session.IsUserLoggedUseCase

class SplashViewModel(
    private val isUserLoggedUseCase: IsUserLoggedUseCase
) : ViewModel() {

    suspend fun checkUserLogged() = isUserLoggedUseCase()
}