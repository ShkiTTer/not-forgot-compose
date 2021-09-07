package ru.shkitter.notforgot.presentation.common.navigation

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.shkitter.notforgot.presentation.common.Screen

@Composable
fun NavigationController(
    startDestination: Screen,
    screens: List<Pair<Screen, @Composable (NavController, Bundle?, Router?) -> Unit>> = emptyList(),
    router: Router? = null
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination.route) {
        screens.forEach { screen ->
            composable(screen.first.route) {
                screen.second.invoke(
                    navController,
                    navController.previousBackStackEntry?.arguments,
                    router
                )
            }
        }
    }
}