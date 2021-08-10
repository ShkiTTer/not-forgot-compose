package ru.shkitter.notforgot.presentation.common.extensions

import androidx.compose.ui.graphics.Color
import android.graphics.Color as AndroidColor

fun Color.Companion.parse(colorString: String) = Color(AndroidColor.parseColor(colorString))