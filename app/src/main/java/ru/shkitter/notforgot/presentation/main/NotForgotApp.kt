package ru.shkitter.notforgot.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.shkitter.notforgot.presentation.auth.LoginScreen
import ru.shkitter.notforgot.presentation.common.Screen
import ru.shkitter.notforgot.presentation.splash.SplashScreen

@Composable
fun NotForgotApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) {
            SplashScreen()
            rememberCoroutineScope().launch {
                delay(2000)
                navController.navigate(Screen.Login.route)
            }
        }

        composable(Screen.Login.route) {
            LoginScreen()
        }

        composable(Screen.Registration.route) {

        }
    }
}