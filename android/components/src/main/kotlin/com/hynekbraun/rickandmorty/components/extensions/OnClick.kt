package com.hynekbraun.rickandmorty.components.extensions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

public fun Modifier.clickableWithoutRipple(
    enabled: Boolean = true,
    onClick: () -> Unit,
): Modifier = composed {
    this.clickable(
        interactionSource = remember { MutableInteractionSource() },
        enabled = enabled,
        indication = null,
        onClick = onClick,
    )
}