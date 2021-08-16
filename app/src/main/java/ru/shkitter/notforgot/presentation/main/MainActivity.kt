package ru.shkitter.notforgot.presentation.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import ru.shkitter.notforgot.presentation.common.theme.NotForgotTheme

@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {

    companion object {
        fun newIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotForgotTheme {
                ProvideWindowInsets {
                    AppRouter()
                }
            }
        }
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }
}