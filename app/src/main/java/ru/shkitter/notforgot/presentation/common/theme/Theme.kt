package ru.shkitter.notforgot.presentation.common.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColors(
    primary = MainBg,
    primaryVariant = StatusBarColor,
    secondary = AccentBlack,
    surface = MainBg,
    background = MainBg,
    onSecondary = TextWhite,
    onPrimary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black
)

@Composable
fun NotForgotTheme(content: @Composable() () -> Unit) {
    MaterialTheme(
        colors = LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}