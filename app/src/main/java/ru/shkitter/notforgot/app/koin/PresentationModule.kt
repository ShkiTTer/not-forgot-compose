package ru.shkitter.notforgot.app.koin

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import ru.shkitter.notforgot.presentation.auth.LoginViewModel
import ru.shkitter.notforgot.presentation.auth.RegistrationViewModel

private val authModule = module {
    viewModel {
        LoginViewModel()
    }

    viewModel { (email: String) ->
        RegistrationViewModel(inputEmail = email)
    }
}

internal val presentationModule = module {
    loadKoinModules(listOf(authModule))
}