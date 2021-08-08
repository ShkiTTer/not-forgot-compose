package ru.shkitter.notforgot.presentation.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.insets.systemBarsPadding
import org.koin.androidx.compose.getViewModel
import ru.shkitter.notforgot.R
import ru.shkitter.notforgot.presentation.common.components.AppFilledButton
import ru.shkitter.notforgot.presentation.common.components.AppOutlinedTextField
import ru.shkitter.notforgot.presentation.common.theme.AccentBlue
import ru.shkitter.notforgot.presentation.common.theme.BgBlack

@Preview(showSystemUi = true, showBackground = true, device = Devices.PIXEL_3)
@Composable
private fun DefaultLoginScreen() {
    LoginScreen {

    }
}

@Composable
fun LoginScreen(onRegistrationClick: (String) -> Unit) {
    val viewModel = getViewModel<LoginViewModel>()

    val email by viewModel.email.observeAsState("")
    val password by viewModel.password.observeAsState("")

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.common_sign_in),
                        style = MaterialTheme.typography.h3
                    )
                },
                backgroundColor = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.onBackground,
                elevation = 8.dp,
                modifier = Modifier.statusBarsPadding()
            )
        }
    ) { _ ->
        Box(
            modifier = Modifier
                .systemBarsPadding()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                Spacer(modifier = Modifier.height(60.dp))

                AppOutlinedTextField(
                    value = email,
                    onValueChange = { text -> viewModel.onEmailChanged(text) },
                    modifier = Modifier.fillMaxWidth(),
                    label = stringResource(id = R.string.common_email)
                )

                Spacer(modifier = Modifier.height(24.dp))

                AppOutlinedTextField(
                    value = password,
                    onValueChange = { text -> viewModel.onPasswordChanged(text) },
                    modifier = Modifier.fillMaxWidth(),
                    label = stringResource(id = R.string.common_password),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
                )

                Spacer(modifier = Modifier.height(60.dp))

                AppFilledButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.common_sign_in)
                )

                Spacer(modifier = Modifier.height(20.dp))

                ClickableText(
                    text = buildAnnotatedString {
                        append(stringResource(id = R.string.login_registration_not_clickable))
                        pushStyle(SpanStyle(color = AccentBlue))
                        append(stringResource(id = R.string.login_registration_clickable))
                        pop()
                    },
                    onClick = { onRegistrationClick.invoke(email) },
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}