package ru.shkitter.notforgot.presentation.common.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun BaseDialog(
    confirmButtonText: String,
    onConfirm: () -> Unit,
    dismissButtonText: String? = null,
    onDismissButtonClick: (() -> Unit)? = null,
    content: (@Composable () -> Unit)? = null,
    title: String? = null
) {
    AlertDialog(
        onDismissRequest = { /*TODO*/ },
        confirmButton = { AppTextButton(onClick = onConfirm, text = confirmButtonText) },
        dismissButton = dismissButtonText?.let {
            {
                AppTextButton(
                    onClick = { onDismissButtonClick?.invoke() },
                    text = it
                )
            }
        },
        text = content,
        title = title?.let {
            {
                Text(
                    text = it,
                    style = MaterialTheme.typography.button,
                    color = Color.Black
                )
            }
        }
    )
}