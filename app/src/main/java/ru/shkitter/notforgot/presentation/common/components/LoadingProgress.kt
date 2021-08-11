package ru.shkitter.notforgot.presentation.common.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import ru.shkitter.notforgot.presentation.common.theme.AccentBlackColor

@Preview(showBackground = true)
@Composable
private fun DefaultLoadingProgress() {
    LoadingProgress()
}

@Composable
fun LoadingProgress(progressColor: Color = AccentBlackColor) =
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(color = progressColor)
    }