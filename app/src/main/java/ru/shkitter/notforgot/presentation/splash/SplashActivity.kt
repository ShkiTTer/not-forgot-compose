package ru.shkitter.notforgot.presentation.splash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import ru.shkitter.notforgot.presentation.common.theme.NotForgotTheme
import ru.shkitter.notforgot.presentation.main.MainActivity

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotForgotTheme {
                SplashScreen()
            }
        }

        WindowCompat.setDecorFitsSystemWindows(window, false)

        lifecycleScope.launchWhenStarted {
            delay(2000)
            startActivity(MainActivity.newIntent(this@SplashActivity))
            finish()
        }
    }
}