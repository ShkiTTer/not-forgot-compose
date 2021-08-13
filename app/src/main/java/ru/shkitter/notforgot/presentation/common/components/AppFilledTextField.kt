package ru.shkitter.notforgot.presentation.common.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.shkitter.notforgot.presentation.common.theme.*

@Preview(showBackground = true)
@Composable
private fun DefaultAppFilledTextField() {
    AppFilledTextField(
        value = "",
        label = "Example",
        onValueChange = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun PlaceholderAppFilledTextField() {
    AppFilledTextField(
        value = "",
        placeholder = "Example",
        onValueChange = {}
    )
}

@Composable
fun AppFilledTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(
        focusedLabelColor = AccentBlueColor,
        cursorColor = BgBlackColor,
        textColor = TextBlackColor,
        unfocusedLabelColor = TextGrayColor,
        focusedIndicatorColor = AccentBlueColor,
        unfocusedIndicatorColor = BgGrayColor,
        placeholderColor = TextGrayColor,
        backgroundColor = BgTextField
    ),
    error: String? = null,
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    maxLines: Int = Int.MAX_VALUE
) {

    Column {
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier,
            textStyle = MaterialTheme.typography.body1,
            keyboardOptions = keyboardOptions,
            singleLine = singleLine,
            enabled = enabled,
            isError = !error.isNullOrEmpty(),
            visualTransformation = visualTransformation,
            label = {
                Text(text = label, style = MaterialTheme.typography.caption)
            },
            colors = colors,
            maxLines = maxLines
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

@Composable
fun AppFilledTextField(
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(
        focusedLabelColor = AccentBlueColor,
        cursorColor = BgBlackColor,
        textColor = TextBlackColor,
        unfocusedLabelColor = TextGrayColor,
        focusedIndicatorColor = AccentBlueColor,
        unfocusedIndicatorColor = BgGrayColor,
        placeholderColor = TextGrayColor,
        backgroundColor = BgTextField
    ),
    error: String? = null,
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    maxLines: Int = Int.MAX_VALUE
) {

    Column {
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier,
            textStyle = MaterialTheme.typography.body1,
            keyboardOptions = keyboardOptions,
            singleLine = singleLine,
            enabled = enabled,
            isError = !error.isNullOrEmpty(),
            visualTransformation = visualTransformation,
            placeholder = {
                Text(text = placeholder, style = MaterialTheme.typography.body1)
            },
            colors = colors,
            maxLines = maxLines
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