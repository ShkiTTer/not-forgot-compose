package ru.shkitter.notforgot.presentation.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import ru.shkitter.notforgot.R
import ru.shkitter.notforgot.presentation.auth.login.model.LoginAction
import ru.shkitter.notforgot.presentation.common.components.AppFilledButton
import ru.shkitter.notforgot.presentation.common.components.AppOutlinedTextField
import ru.shkitter.notforgot.presentation.common.components.ContentStateBox
import ru.shkitter.notforgot.presentation.common.theme.AccentBlue
import ru.shkitter.notforgot.presentation.common.theme.BgMain

@Preview(showSystemUi = true, showBackground = true, device = Devices.PIXEL_3)
@Composable
private fun DefaultLoginScreen() {
    LoginScreen {}
}

@Composable
fun LoginScreen(onRegistrationClick: (String) -> Unit) {
    val viewModel = getViewModel<LoginViewModel>()
    val event by viewModel.event.observeAsState()
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(
        scaffoldState = scaffoldState,
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
        },
        snackbarHost = {
            scaffoldState.snackbarHostState
        }) { _ ->

        event?.getContentIfNotHandled()?.let { action ->
            when (action) {
                is LoginAction.Succeeded -> Unit
                is LoginAction.Error -> coroutineScope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = context.getString(R.string.common_something_went_wrong)
                    )
                }
            }
        }

        ContentStateBox(
            viewModel = viewModel,
            snackbarHostState = scaffoldState.snackbarHostState,
            modifier = Modifier
                .fillMaxSize()
                .background(color = BgMain)
                .systemBarsPadding(),
            content = {
                LoginContent(
                    viewModel = viewModel,
                    onRegistrationClick = onRegistrationClick
                )
            })
    }
}

@Composable
fun LoginContent(viewModel: LoginViewModel, onRegistrationClick: (String) -> Unit) {
    val email by viewModel.email.observeAsState("")
    val password by viewModel.password.observeAsState("")

    val emailError by viewModel.emailError.observeAsState()
    val passwordError by viewModel.passwordError.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier.height(60.dp))

        AppOutlinedTextField(
            value = email,
            onValueChange = viewModel::onEmailChanged,
            modifier = Modifier.fillMaxWidth(),
            label = stringResource(id = R.string.common_email),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            error = emailError?.let { stringResource(id = it) }
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
                imeAction = ImeAction.Done
            ),
            error = passwordError?.let { stringResource(id = it) }
        )

        Spacer(modifier = Modifier.height(60.dp))

        AppFilledButton(
            onClick = { viewModel.login() },
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