package ru.shkitter.notforgot.presentation.common.navigation

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import ru.shkitter.notforgot.presentation.common.Screen

fun NavController.navigate(
    screen: Screen,
    params: Bundle? = null,
    builder: NavOptionsBuilder.() -> Unit = {}
) {
    this.currentBackStackEntry?.replaceArguments(params)
    navigate(screen.route, builder)
}
