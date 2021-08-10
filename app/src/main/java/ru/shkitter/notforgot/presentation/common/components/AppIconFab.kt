package ru.shkitter.notforgot.presentation.common.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun DefaultAppIconFab() {
    AppIconFab(onClick = { /*TODO*/ }, icon = Icons.Filled.Add)
}

@Composable
fun AppIconFab(
    onClick: () -> Unit,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.secondary,
    contentColor: Color = MaterialTheme.colors.onSecondary
) {
    FloatingActionButton(
        onClick = onClick,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        modifier = modifier
    ) {
        Icon(imageVector = icon, contentDescription = null)
    }
}

@Composable
fun AppIconFab(
    onClick: () -> Unit,
    icon: Painter,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.secondary,
    contentColor: Color = MaterialTheme.colors.onSecondary
) {
    FloatingActionButton(
        onClick = onClick,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        modifier = modifier
    ) {
        Icon(painter = icon, contentDescription = null)
    }
}