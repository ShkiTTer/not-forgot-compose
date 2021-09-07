package ru.shkitter.notforgot.presentation.main

import androidx.compose.runtime.Composable
import ru.shkitter.domain.task.model.Task
import ru.shkitter.notforgot.presentation.auth.login.LoginScreen
import ru.shkitter.notforgot.presentation.auth.registration.RegistrationScreen
import ru.shkitter.notforgot.presentation.common.Screen
import ru.shkitter.notforgot.presentation.common.extensions.getExtra
import ru.shkitter.notforgot.presentation.common.navigation.NavigationController
import ru.shkitter.notforgot.presentation.splash.SplashScreen
import ru.shkitter.notforgot.presentation.task.create.CreateTaskScreen
import ru.shkitter.notforgot.presentation.task.details.TaskDetailsScreen
import ru.shkitter.notforgot.presentation.task.list.TaskListScreen

@Composable
fun AppRouter() {
    NavigationController(startDestination = Screen.Splash, screens = listOf(
        Screen.Splash to { navController, _, _ -> SplashScreen(navController = navController) },
        Screen.Login to { navController, _, _ -> LoginScreen(navController = navController) },
        Screen.Registration to { navController, bundle, _ ->
            RegistrationScreen(
                navController = navController,
                inputEmail = bundle?.getExtra<String>(Screen.Registration.PARAM_EMAIL).orEmpty()
            )
        },
        Screen.TaskList to { navController, _, _ -> TaskListScreen(navController = navController) },
        Screen.TaskDetails to { navController, bundle, _ ->
            bundle.getExtra<Task>(Screen.TaskDetails.PARAM_TASK)?.let { task ->
                TaskDetailsScreen(
                    task = task,
                    navController = navController
                )
            }
        },
        Screen.CreateTask to { navController, bundle, _ ->
            CreateTaskScreen(
                task = bundle.getExtra(Screen.CreateTask.PARAM_TASK),
                navController = navController
            )
        }
    ))
}