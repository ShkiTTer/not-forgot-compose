package ru.shkitter.notforgot.presentation.common.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsPadding

@Preview
@Composable
fun DefaultBaseTopAppBar() {
    BaseTopAppBar(title = "Example")
}

@Composable
fun BaseTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    hasStatusBarPadding: Boolean = true
) {

    val statusBarPaddingModifier = if (hasStatusBarPadding) {
        Modifier.statusBarsPadding()
    } else Modifier

    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.h3
            )
        },
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onBackground,
        elevation = 8.dp,
        modifier = modifier.then(statusBarPaddingModifier)
    )
}