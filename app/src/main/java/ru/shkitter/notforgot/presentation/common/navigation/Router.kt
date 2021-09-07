package ru.shkitter.notforgot.presentation.common.navigation

import android.os.Bundle
import androidx.core.os.bundleOf

interface Router {
    fun routeTo(route: String, params: Bundle = bundleOf())
}

fun createExternalRouter(block: (String, Bundle) -> Unit) = object : Router {
    override fun routeTo(route: String, params: Bundle) {
        block(route, params)
    }
}