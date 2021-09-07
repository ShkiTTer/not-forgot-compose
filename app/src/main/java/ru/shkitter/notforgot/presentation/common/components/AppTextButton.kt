package ru.shkitter.notforgot.presentation.common.components

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import ru.shkitter.notforgot.presentation.common.theme.AccentBlueColor
import ru.shkitter.notforgot.presentation.common.theme.BgBlackColor

@Preview(showBackground = true)
@Composable
private fun DefaultAppFilledButton() {
    AppTextButton(onClick = { /*TODO*/ }, text = "Example")
}

@Composable
fun AppTextButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    isUpperCase: Boolean = true
) {
    TextButton(
        onClick = onClick,
        colors = ButtonDefaults.textButtonColors(contentColor = AccentBlueColor),
        modifier = modifier
    ) {
        Text(
            text = if (isUpperCase) text.uppercase() else text,
            style = MaterialTheme.typography.button
        )
    }
}