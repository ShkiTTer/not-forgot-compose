package ru.shkitter.notforgot.app.koin

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import ru.shkitter.notforgot.presentation.auth.LoginViewModel

private val authModule = module {
    viewModel {
        LoginViewModel()
    }
}

internal val presentationModule = module {
    loadKoinModules(listOf(authModule))
}