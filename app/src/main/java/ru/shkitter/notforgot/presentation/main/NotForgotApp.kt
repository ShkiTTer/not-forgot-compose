package ru.shkitter.notforgot.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.shkitter.notforgot.presentation.auth.LoginScreen
import ru.shkitter.notforgot.presentation.auth.RegistrationScreen
import ru.shkitter.notforgot.presentation.common.Screen
import ru.shkitter.notforgot.presentation.splash.SplashScreen

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
    }
}