package ru.shkitter.notforgot.presentation.common.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.shkitter.notforgot.R
import ru.shkitter.notforgot.presentation.common.theme.*

@Preview(showBackground = true)
@Composable
private fun DefaultAppFilledTextField() {
    AppFilledTextField(
        value = "",
        label = "Example",
        onValueChange = {},
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
private fun PlaceholderAppFilledTextField() {
    AppFilledTextField(
        value = "",
        placeholder = "Example",
        onValueChange = {},
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
private fun CounterAppFilledTextField() {
    AppFilledTextField(
        value = "123",
        onValueChange = {},
        showCounter = true,
        maxLength = 50,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun AppFilledTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
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
    maxLines: Int = Int.MAX_VALUE,
    maxLength: Int = Int.MAX_VALUE,
    showCounter: Boolean = false
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
            label = label?.let {
                {
                    Text(text = it, style = MaterialTheme.typography.caption)
                }
            },
            placeholder = placeholder?.let {
                {
                    Text(text = it, style = MaterialTheme.typography.body1)
                }
            },
            colors = colors,
            maxLines = maxLines
        )

        when {
            !error.isNullOrEmpty() -> Text(
                text = error,
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.error,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            showCounter -> Text(
                text = stringResource(id = R.string.common_counter, value.length, maxLength),
                style = MaterialTheme.typography.caption,
                color = TextBlackColor,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )
        }
    }
}