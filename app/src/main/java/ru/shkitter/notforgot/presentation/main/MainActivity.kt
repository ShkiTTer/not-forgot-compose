package ru.shkitter.notforgot.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import ru.shkitter.notforgot.presentation.common.theme.NotForgotTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotForgotTheme {
                ProvideWindowInsets {
                    NotForgotApp()
                }
            }
        }
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }
}