package ru.shkitter.notforgot.presentation.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.ui.TopAppBar
import ru.shkitter.notforgot.R
import ru.shkitter.notforgot.presentation.common.theme.AccentBlue
import ru.shkitter.notforgot.presentation.common.theme.SplashBg

@Preview(showSystemUi = true, showBackground = true, device = Devices.PIXEL_3)
@Composable
private fun DefaultLoginScreen() {
    LoginScreen {

    }
}

@Composable
fun LoginScreen(onRegistrationClick: () -> Unit) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.login_title)) },
                backgroundColor = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.onBackground,
                elevation = 8.dp,
                contentPadding = rememberInsetsPaddingValues(
                    insets = LocalWindowInsets.current.statusBars,
                    applyStart = true,
                    applyTop = true,
                    applyEnd = true
                )
            )
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                Spacer(modifier = Modifier.height(60.dp))

                OutlinedTextField(
                    value = email.value,
                    onValueChange = { text -> email.value = text },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = stringResource(id = R.string.login_email))
                    })

                Spacer(modifier = Modifier.height(24.dp))

                OutlinedTextField(
                    value = password.value,
                    onValueChange = { text -> password.value = text },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = stringResource(id = R.string.login_password))
                    })

                Spacer(modifier = Modifier.height(60.dp))

                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(backgroundColor = SplashBg),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.login_title).uppercase(),
                        style = MaterialTheme.typography.button,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                ClickableText(
                    text = buildAnnotatedString {
                        append(stringResource(id = R.string.login_registration_not_clickable))
                        pushStyle(SpanStyle(color = AccentBlue))
                        append(stringResource(id = R.string.login_registration_clickable))
                        pop()
                    },
                    onClick = {},
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}