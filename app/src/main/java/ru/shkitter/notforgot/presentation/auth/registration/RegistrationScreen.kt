package ru.shkitter.notforgot.presentation.auth.registration

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.insets.systemBarsPadding
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import ru.shkitter.notforgot.R
import ru.shkitter.notforgot.presentation.common.components.AppFilledButton
import ru.shkitter.notforgot.presentation.common.components.AppOutlinedTextField
import ru.shkitter.notforgot.presentation.common.theme.AccentBlue

@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_3)
@Composable
private fun DefaultRegistrationScreen() {
    RegistrationScreen("") {

    }
}

@Composable
fun RegistrationScreen(inputEmail: String, onSignInClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.registration_title),
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

        val viewModel = getViewModel<RegistrationViewModel> {
            parametersOf(inputEmail)
        }

        val email by viewModel.email.observeAsState("")
        val name by viewModel.name.observeAsState("")
        val password by viewModel.password.observeAsState("")
        val repeatPassword by viewModel.repeatPassword.observeAsState("")

        Box(modifier = Modifier.systemBarsPadding()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                Spacer(modifier = Modifier.height(36.dp))

                AppOutlinedTextField(
                    value = email,
                    onValueChange = viewModel::onEmailChanged,
                    modifier = Modifier.fillMaxWidth(),
                    label = stringResource(id = R.string.common_email),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                AppOutlinedTextField(
                    value = name,
                    onValueChange = viewModel::onNameChanged,
                    modifier = Modifier.fillMaxWidth(),
                    label = stringResource(id = R.string.registration_name),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
                )

                Spacer(modifier = Modifier.height(24.dp))

                AppOutlinedTextField(
                    value = password,
                    onValueChange = viewModel::onPasswordChanged,
                    modifier = Modifier.fillMaxWidth(),
                    label = stringResource(id = R.string.common_password),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                AppOutlinedTextField(
                    value = repeatPassword,
                    onValueChange = viewModel::onRepeatPasswordChanged,
                    modifier = Modifier.fillMaxWidth(),
                    label = stringResource(id = R.string.registration_repeat_password),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    )
                )

                Spacer(modifier = Modifier.height(60.dp))

                AppFilledButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.registration_create)
                )

                Spacer(modifier = Modifier.height(20.dp))

                ClickableText(
                    text = buildAnnotatedString {
                        append(stringResource(id = R.string.registration_have_account))
                        pushStyle(SpanStyle(color = AccentBlue))
                        append(stringResource(id = R.string.common_sign_in))
                        pop()
                    },
                    onClick = { onSignInClick.invoke() },
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}