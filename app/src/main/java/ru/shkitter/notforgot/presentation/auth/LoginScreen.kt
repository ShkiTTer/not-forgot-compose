package ru.shkitter.notforgot.presentation.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.shkitter.notforgot.R

@Preview(showSystemUi = true, showBackground = true, device = Devices.PIXEL_3)
@Composable
fun LoginScreen() {
    val email = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.login_title))
                },
                backgroundColor = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.onBackground,
                elevation = 8.dp
            )
        }
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            )

            OutlinedTextField(
                value = email.value,
                onValueChange = { text -> email.value = text },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(
                        text = "Email"
                    )
                })
        }
    }
}