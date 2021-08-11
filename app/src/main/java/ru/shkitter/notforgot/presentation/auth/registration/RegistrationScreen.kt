package ru.shkitter.notforgot.presentation.auth.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
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
import com.google.accompanist.insets.navigationBarsWithImePadding
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import ru.shkitter.notforgot.R
import ru.shkitter.notforgot.presentation.auth.registration.model.RegistrationAction
import ru.shkitter.notforgot.presentation.common.components.AppFilledButton
import ru.shkitter.notforgot.presentation.common.components.AppOutlinedTextField
import ru.shkitter.notforgot.presentation.common.components.BaseTopAppBar
import ru.shkitter.notforgot.presentation.common.components.ContentStateBox
import ru.shkitter.notforgot.presentation.common.theme.AccentBlueColor
import ru.shkitter.notforgot.presentation.common.theme.BgMainColor

@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_3)
@Composable
private fun DefaultRegistrationScreen() {
    RegistrationScreen(inputEmail = "", onSignInClick = { /*TODO*/ }) {

    }
}

@Composable
fun RegistrationScreen(
    inputEmail: String,
    onSignInClick: () -> Unit,
    onRegisterSucceeded: () -> Unit
) {
    val viewModel = getViewModel<RegistrationViewModel> {
        parametersOf(inputEmail)
    }
    val event by viewModel.event.observeAsState()

    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = { scaffoldState.snackbarHostState },
        topBar = { BaseTopAppBar(title = stringResource(id = R.string.registration_title)) }
    ) { _ ->

        event?.getContentIfNotHandled()?.let { action ->
            when (action) {
                is RegistrationAction.Succeeded -> onRegisterSucceeded.invoke()
                is RegistrationAction.Error -> coroutineScope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = context.getString(R.string.common_something_went_wrong)
                    )
                }
            }
        }

        ContentStateBox(
            viewModel = viewModel,
            content = { RegistrationContent(viewModel = viewModel, onSignInClick = onSignInClick) },
            snackbarHostState = scaffoldState.snackbarHostState,
            modifier = Modifier
                .fillMaxSize()
                .background(color = BgMainColor)
        )
    }
}

@Composable
fun RegistrationContent(viewModel: RegistrationViewModel, onSignInClick: () -> Unit) {
    val email by viewModel.email.observeAsState("")
    val name by viewModel.name.observeAsState("")
    val password by viewModel.password.observeAsState("")
    val repeatPassword by viewModel.repeatPassword.observeAsState("")

    val emailError by viewModel.emailError.observeAsState()
    val nameError by viewModel.nameError.observeAsState()
    val passwordError by viewModel.passwordError.observeAsState()
    val repeatPasswordError by viewModel.repeatPasswordError.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .navigationBarsWithImePadding()
            .verticalScroll(rememberScrollState())
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
            ),
            error = emailError?.let { stringResource(id = it) }
        )

        Spacer(modifier = Modifier.height(24.dp))

        AppOutlinedTextField(
            value = name,
            onValueChange = viewModel::onNameChanged,
            modifier = Modifier.fillMaxWidth(),
            label = stringResource(id = R.string.registration_name),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            error = nameError?.let { stringResource(id = it) }
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
            ),
            error = passwordError?.let { stringResource(id = it) }
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
            ),
            error = repeatPasswordError?.let { stringResource(id = it) }
        )

        Spacer(modifier = Modifier.height(60.dp))

        AppFilledButton(
            onClick = { viewModel.register() },
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.registration_create)
        )

        Spacer(modifier = Modifier.height(20.dp))

        ClickableText(
            text = buildAnnotatedString {
                append(stringResource(id = R.string.registration_have_account))
                pushStyle(SpanStyle(color = AccentBlueColor))
                append(stringResource(id = R.string.common_sign_in))
                pop()
            },
            onClick = { onSignInClick.invoke() },
            style = MaterialTheme.typography.body2,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}