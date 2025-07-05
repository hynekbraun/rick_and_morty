package com.hynekbraun.rickandmorty.screens.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.hynekbraun.rickandmorty.components.theme.RMTheme

@Composable
internal fun LoadingScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {

        CircularProgressIndicator(
            modifier = Modifier.fillMaxWidth(0.4f),
            color = RMTheme.colors.accentPrimary,
            strokeCap = StrokeCap.Round,
            strokeWidth = 8.dp
        )
    }
}
