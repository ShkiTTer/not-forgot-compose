package ru.shkitter.notforgot.presentation.common.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.shkitter.notforgot.presentation.common.theme.AccentBlue
import ru.shkitter.notforgot.presentation.common.theme.BgBlack
import ru.shkitter.notforgot.presentation.common.theme.TextWhite

@Composable
fun AppSnackbar(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    SnackbarHost(hostState = snackbarHostState, modifier = modifier) { data ->
        Snackbar(
            backgroundColor = BgBlack,
            modifier = Modifier.padding(16.dp),
            action = {
                data.actionLabel?.let { label ->
                    Text(
                        text = label,
                        style = MaterialTheme.typography.h4,
                        color = AccentBlue
                    )
                }
            }) {
            Text(text = data.message, style = MaterialTheme.typography.h5, color = TextWhite)
        }
    }
}