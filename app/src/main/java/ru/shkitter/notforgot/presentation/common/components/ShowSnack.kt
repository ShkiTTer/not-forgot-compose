package ru.shkitter.notforgot.presentation.common.components

import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import ru.shkitter.notforgot.R

@Composable
fun ShowSnack(
    snackHostState: SnackbarHostState,
    message: String = stringResource(id = R.string.common_something_went_wrong),
    duration: SnackbarDuration = SnackbarDuration.Short,
    action: String? = null,
    onActionClick: () -> Unit = {}
) {
    val channel = remember { Channel<Int>(Channel.Factory.CONFLATED) }

    LaunchedEffect(channel) {
        channel.receiveAsFlow().collect {
            val result = snackHostState.showSnackbar(
                message = message,
                actionLabel = action,
                duration = duration
            )

            when (result) {
                SnackbarResult.ActionPerformed -> onActionClick.invoke()
                SnackbarResult.Dismissed -> Unit
            }
        }
    }
}