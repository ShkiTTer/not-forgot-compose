package ru.shkitter.notforgot.app.koin

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import ru.shkitter.domain.task.model.Task
import ru.shkitter.notforgot.presentation.auth.login.LoginViewModel
import ru.shkitter.notforgot.presentation.auth.registration.RegistrationViewModel
import ru.shkitter.notforgot.presentation.splash.SplashViewModel
import ru.shkitter.notforgot.presentation.task.create.CreateTaskViewModel
import ru.shkitter.notforgot.presentation.task.list.TaskListViewModel

private val authModule = module {
    viewModel {
        SplashViewModel(isUserLoggedUseCase = get())
    }

    viewModel {
        LoginViewModel(
            loginUseCase = get(),
            validationUseCase = get()
        )
    }

    viewModel { (email: String) ->
        RegistrationViewModel(
            inputEmail = email,
            registrationUseCase = get(),
            validationUseCase = get()
        )
    }
}

private val taskModule = module {
    viewModel {
        TaskListViewModel(getTaskListUseCase = get())
    }

    viewModel { (task: Task) ->
        CreateTaskViewModel(
            task = task,
            getCreateTaskDataUseCase = get()
        )
    }
}

internal val presentationModule = module {
    loadKoinModules(listOf(authModule, taskModule))
}