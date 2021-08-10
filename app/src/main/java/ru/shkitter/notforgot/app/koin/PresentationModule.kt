package ru.shkitter.notforgot.app.koin

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import ru.shkitter.notforgot.presentation.auth.login.LoginViewModel
import ru.shkitter.notforgot.presentation.auth.registration.RegistrationViewModel

private val authModule = module {
    viewModel {
        LoginViewModel(
            loginUseCase = get(),
            validationUseCase = get()
        )
    }

    viewModel { (email: String) ->
        RegistrationViewModel(
            inputEmail = email,
            registrationUseCase = get()
        )
    }
}

internal val presentationModule = module {
    loadKoinModules(listOf(authModule))
}