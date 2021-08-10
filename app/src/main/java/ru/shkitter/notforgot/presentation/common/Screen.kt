package ru.shkitter.notforgot.presentation.common

sealed interface Screen {
    val route: String

    object Splash: Screen {
        override val route: String = "splash"
    }

    object Login: Screen {
        override val route: String = "login"
    }

    object Registration: Screen {
        const val PARAM_EMAIL = "email"

        override val route: String = "registration?email={$PARAM_EMAIL}"

        fun createRouteWithEmail(email: String) = "registration?email=$email"
    }

    object TaskList: Screen {
        override val route: String = "task-list"
    }
}
