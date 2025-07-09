package com.hynekbraun.rickandmorty.components.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private const val COLOR = 0xFFF3CABE

@Composable
internal fun SnapshotPreviewColumn(
    spacing: Dp = 4.dp,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = Modifier.background(Color(COLOR)),
        verticalArrangement = Arrangement.spacedBy(spacing),
    ) {
        content()
    }
}