package com.hynekbraun.rickandmorty.components.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember

public object RMTheme {

    public val colors: RMColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    public val typography: RMTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}

@Composable
public fun RMTheme(
    typography: RMTypography = RMTheme.typography,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val currentColor = remember { if (darkTheme) darkColors() else lightColors() }
    val rememberedColors = remember { currentColor.copy() }.apply { updateColorsFrom(currentColor) }
    CompositionLocalProvider(
        LocalColors provides rememberedColors,
        LocalTypography provides typography,
    ) {
        content()
    }
}