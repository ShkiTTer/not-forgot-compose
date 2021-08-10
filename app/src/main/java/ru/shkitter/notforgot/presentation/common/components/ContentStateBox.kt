package ru.shkitter.notforgot.presentation.common.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.accompanist.insets.systemBarsPadding
import ru.shkitter.notforgot.presentation.common.state.ContentState
import ru.shkitter.notforgot.presentation.common.state.StateViewModel

@Composable
fun <A, VM : StateViewModel<A>> ContentStateBox(
    viewModel: VM,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    initialState: ContentState = ContentState.Content,
    loading: @Composable () -> Unit = { LoadingProgress() },
    empty: @Composable () -> Unit = {},
    error: @Composable () -> Unit = {},
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    snackbar: (@Composable (SnackbarHostState) -> Unit)? = null
) = Box(modifier = modifier) {

    val state by viewModel.contentState.observeAsState(initial = initialState)

    when (state) {
        is ContentState.Loading -> loading.invoke()
        is ContentState.Empty -> empty.invoke()
        is ContentState.Content -> content.invoke()
        is ContentState.Error -> error.invoke()
    }

    snackbar?.invoke(snackbarHostState) ?: AppSnackbar(
        snackbarHostState = snackbarHostState,
        modifier = Modifier
            .align(Alignment.BottomCenter)
    )
}