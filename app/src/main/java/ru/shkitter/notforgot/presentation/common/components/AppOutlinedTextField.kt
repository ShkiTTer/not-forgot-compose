package ru.shkitter.notforgot.presentation.common.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.shkitter.notforgot.presentation.common.theme.*


@Preview(showBackground = true)
@Composable
private fun DefaultAppOutlinedTextField() {
    AppOutlinedTextField(
        value = "",
        label = "Example",
        onValueChange = {}
    )
}

@Composable
fun AppOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    error: String? = null,
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    Column {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier,
            textStyle = MaterialTheme.typography.body1,
            keyboardOptions = keyboardOptions,
            singleLine = singleLine,
            isError = !error.isNullOrEmpty(),
            label = {
                Text(text = label, style = MaterialTheme.typography.caption)
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = AccentBlue,
                focusedLabelColor = AccentBlue,
                cursorColor = BgBlack,
                textColor = TextBlack,
                unfocusedBorderColor = BgGray,
                unfocusedLabelColor = TextGray
            )
        )

        if (!error.isNullOrEmpty()) {
            Text(
                text = error,
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.error,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}