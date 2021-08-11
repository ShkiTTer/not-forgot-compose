package ru.shkitter.notforgot.presentation.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import ru.shkitter.domain.task.model.Task
import ru.shkitter.notforgot.presentation.auth.login.LoginScreen
import ru.shkitter.notforgot.presentation.auth.registration.RegistrationScreen
import ru.shkitter.notforgot.presentation.common.Screen
import ru.shkitter.notforgot.presentation.splash.SplashScreen
import ru.shkitter.notforgot.presentation.task.details.TaskDetailsScreen
import ru.shkitter.notforgot.presentation.task.list.TaskListScreen

@Composable
fun NotForgotApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Splash.route) {

        composable(Screen.Splash.route) {
            SplashScreen { isUserLogged ->
                navController.popBackStack()
                navController.navigate(if (isUserLogged) Screen.TaskList.route else Screen.Login.route)
            }
        }

        composable(Screen.Login.route) {
            LoginScreen(
                onRegistrationClick = { email ->
                    navController.navigate(Screen.Registration.createRouteWithEmail(email)) {
                        popUpTo(Screen.Login.route)
                    }
                },
                onSucceededLogin = {
                    navController.popBackStack()
                    navController.navigate(Screen.TaskList.route) {
                        launchSingleTop = true
                    }
                })
        }

        composable(
            Screen.Registration.route,
            arguments = listOf(navArgument(Screen.Registration.PARAM_EMAIL) {
                defaultValue = ""
            })
        ) { backStackEntry ->
            RegistrationScreen(
                inputEmail = backStackEntry.arguments?.getString(Screen.Registration.PARAM_EMAIL)
                    .orEmpty(),
                onSignInClick = {
                    navController.popBackStack()
                },
                onRegisterSucceeded = {
                    navController.popBackStack()
                    navController.navigate(Screen.TaskList.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.TaskList.route) {
            TaskListScreen { task ->
                navController.currentBackStackEntry?.arguments?.putSerializable(
                    Screen.TaskDetails.PARAM_TASK,
                    task
                )
                navController.navigate(Screen.TaskDetails.route) {
                    popUpTo(Screen.TaskList.route)
                }
            }
        }

        composable(Screen.TaskDetails.route, arguments = listOf(
            navArgument(Screen.TaskDetails.PARAM_TASK) {
                type = NavType.SerializableType(Task::class.java)
            }
        )) {
            val task =
                navController.previousBackStackEntry?.arguments?.getSerializable(Screen.TaskDetails?.PARAM_TASK) as? Task
                    ?: return@composable
            TaskDetailsScreen(task = task)
        }
    }
}