package com.hynekbraun.rickandmorty.screens.pictureDialog

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.hynekbraun.rickandmorty.components.util.CachedAsyncImage

@Composable
internal fun PictureDialog(pictureUrl: String) {
    CachedAsyncImage(
        model = pictureUrl,
        modifier = Modifier
            .aspectRatio(9f / 10f)
            .clip(RoundedCornerShape(12.dp)),
    )
}
