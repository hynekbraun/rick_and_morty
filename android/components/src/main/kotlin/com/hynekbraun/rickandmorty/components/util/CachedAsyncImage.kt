package com.hynekbraun.rickandmorty.components.util

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import coil3.request.ImageRequest

@Composable
public fun CachedAsyncImage(
    model: String?,
    modifier: Modifier = Modifier,
    onSuccess: ((AsyncImagePainter.State.Success) -> Unit)? = null,
    fallback: Painter? = null,
    contentScale: ContentScale = ContentScale.Crop,
    context: Context = LocalContext.current
) {
    AsyncImage(
        model = ImageRequest.Builder(context)
            .diskCacheKey(model)
            .memoryCacheKey(model)
            .data(model).build(),
        modifier = modifier,
        contentDescription = null,
        contentScale = contentScale,
        onSuccess = onSuccess,
        fallback = fallback,

    )
}
