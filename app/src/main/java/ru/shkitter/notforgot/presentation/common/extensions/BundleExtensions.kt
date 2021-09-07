package ru.shkitter.notforgot.presentation.common.extensions

import android.os.Bundle

inline fun <reified T> Bundle?.getExtra(key: String): T? = this?.get(key) as? T

inline fun <reified T> Bundle?.getExtra(key: String, default: T): T = this?.get(key) as? T ?: default