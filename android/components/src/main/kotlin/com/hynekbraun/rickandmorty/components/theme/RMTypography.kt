package com.hynekbraun.rickandmorty.components.theme

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

public data class RMTypography(
    val headline1: TextStyle = TextStyle(fontSize = 28.sp, lineHeight = 34.sp, fontWeight = FontWeight.Bold),
    val headline2: TextStyle = TextStyle(fontSize = 20.sp, lineHeight = 24.sp, fontWeight = FontWeight.Bold),
    val headline3: TextStyle = TextStyle(fontSize = 16.sp, lineHeight = 20.sp, fontWeight = FontWeight.Bold),
    val paragraphLarge: TextStyle = TextStyle(fontSize = 18.sp, lineHeight = 22.sp, fontWeight = FontWeight.Normal),
    val paragraphMedium: TextStyle = TextStyle(fontSize = 16.sp, lineHeight = 20.sp, fontWeight = FontWeight.Normal),
    val paragraphSmall: TextStyle = TextStyle(fontSize = 14.sp, lineHeight = 18.sp, fontWeight = FontWeight.Normal),
    val bottomNavigation: TextStyle = TextStyle(fontSize = 12.sp, lineHeight = TextUnit.Unspecified, fontWeight = FontWeight.Normal),
)
internal val LocalTypography: ProvidableCompositionLocal<RMTypography> = staticCompositionLocalOf { RMTypography() }