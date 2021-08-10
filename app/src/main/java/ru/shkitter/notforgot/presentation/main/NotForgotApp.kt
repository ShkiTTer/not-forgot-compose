package ru.shkitter.notforgot.presentation.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import ru.shkitter.notforgot.presentation.auth.login.LoginScreen
import ru.shkitter.notforgot.presentation.auth.registration.RegistrationScreen
import ru.shkitter.notforgot.presentation.common.Screen
import ru.shkitter.notforgot.presentation.task.list.TaskListScreen

@Composable
fun NotForgotApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) {
            LoginScreen { email ->
                navController.navigate(Screen.Registration.createRouteWithEmail(email)) {
                    popUpTo(Screen.Login.route)
                }
            }
        }

        composable(
            Screen.Registration.route,
            arguments = listOf(navArgument(Screen.Registration.PARAM_EMAIL) {
                defaultValue = ""
            })
        ) { backStackEntry ->
            RegistrationScreen(
                inputEmail = backStackEntry.arguments?.getString(Screen.Registration.PARAM_EMAIL)
                    .orEmpty()
            ) {
                navController.popBackStack()
            }
        }

        composable(Screen.TaskList.route) {
            TaskListScreen()
        }
    }
}