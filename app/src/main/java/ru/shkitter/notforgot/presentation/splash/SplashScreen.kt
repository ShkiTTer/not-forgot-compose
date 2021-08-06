package ru.shkitter.notforgot.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.shkitter.notforgot.R
import ru.shkitter.notforgot.presentation.common.theme.SplashBg
import ru.shkitter.notforgot.presentation.common.theme.TextWhite

@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_3)
@Composable
fun SplashScreen() {
    Surface(color = SplashBg) {
        Column(Modifier.fillMaxSize().padding(16.dp)) {
            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(176.dp)
            )

            Text(
                text = stringResource(R.string.splash_app_name),
                color = TextWhite,
                style = MaterialTheme.typography.h1
            )

            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(106.dp)
            )

            Image(
                painter = painterResource(R.drawable.ic_logo),
                contentDescription = null,
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
}