package ru.shkitter.notforgot.presentation.common

sealed class Screen(val route: String) {
    object Splash: Screen("splash")
    object Login: Screen("login")
    object Registration: Screen("registration/{email}") {
        fun createRouteWithEmail(email: String) = "registration/$email"
    }
}
