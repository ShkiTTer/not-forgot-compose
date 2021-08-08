package ru.shkitter.notforgot.presentation.common.components

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import ru.shkitter.notforgot.presentation.common.theme.BgBlack

@Preview(showBackground = true)
@Composable
private fun DefaultAppFilledButton() {
    AppFilledButton(onClick = { /*TODO*/ }, text = "Example")
}

@Composable
fun AppFilledButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    isUpperCase: Boolean = true
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = BgBlack),
        modifier = modifier
    ) {
        Text(
            text = if (isUpperCase) text.uppercase() else text,
            style = MaterialTheme.typography.button,
            color = Color.White
        )
    }
}