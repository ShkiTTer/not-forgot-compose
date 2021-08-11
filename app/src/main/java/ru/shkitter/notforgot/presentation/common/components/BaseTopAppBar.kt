package ru.shkitter.notforgot.presentation.common.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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

@Preview
@Composable
fun BackBaseTopAppBar() {
    BaseTopAppBar(title = "Example", onBackClick = {})
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

@Composable
fun BaseTopAppBar(
    title: String,
    onBackClick: () -> Unit,
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
        modifier = modifier.then(statusBarPaddingModifier),
        navigationIcon = {
            Icon(imageVector = Icons.Filled.ArrowBack,
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .clickable { onBackClick.invoke() })
        }
    )
}