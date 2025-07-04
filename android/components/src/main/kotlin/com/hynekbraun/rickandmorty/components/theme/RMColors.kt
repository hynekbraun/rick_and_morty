package com.hynekbraun.rickandmorty.components.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

public class RMColors(
    accentPrimary: Color,
    backgroundsPrimary: Color,
    backgroundsSecondary: Color,
    backgroundsTertiary: Color,
    backgroundsBottomNavigation: Color,
    foregroundsPrimary: Color,
    foregroundsSecondary: Color,
    foregroundsTertiary: Color,
    foregroundsSeparator: Color,
    foregroundsOnPrimary: Color,
    iconsPrimary: Color,
    iconsSecondary: Color,
    iconsTertiary: Color,
) {
    public var accentPrimary: Color by mutableStateOf(accentPrimary)
        private set
    public var backgroundsPrimary: Color by mutableStateOf(backgroundsPrimary)
        private set
    public var backgroundsSecondary: Color by mutableStateOf(backgroundsSecondary)
        private set
    public var backgroundsTertiary: Color by mutableStateOf(backgroundsTertiary)
        private set
    public var backgroundsBottomNavigation: Color by mutableStateOf(backgroundsBottomNavigation)
        private set
    public var foregroundsPrimary: Color by mutableStateOf(foregroundsPrimary)
        private set
    public var foregroundsSecondary: Color by mutableStateOf(foregroundsSecondary)
        private set
    public var foregroundsTertiary: Color by mutableStateOf(foregroundsTertiary)
        private set
    public var foregroundsSeparator: Color by mutableStateOf(foregroundsSeparator)
        private set
    public var foregroundsOnPrimary: Color by mutableStateOf(foregroundsOnPrimary)
        private set
    public var iconsPrimary: Color by mutableStateOf(iconsPrimary)
        private set
    public var iconsSecondary: Color by mutableStateOf(iconsSecondary)
        private set
    public var iconsTertiary: Color by mutableStateOf(iconsTertiary)
        private set

    internal fun copy(
        accentPrimary: Color = this.accentPrimary,
        backgroundsPrimary: Color = this.backgroundsPrimary,
        backgroundsSecondary: Color = this.backgroundsSecondary,
        backgroundsTertiary: Color = this.backgroundsTertiary,
        backgroundsBottomNavigation: Color = this.backgroundsBottomNavigation,
        foregroundsPrimary: Color = this.foregroundsPrimary,
        foregroundsSecondary: Color = this.foregroundsSecondary,
        foregroundsTertiary: Color = this.foregroundsTertiary,
        foregroundsSeparator: Color = this.foregroundsSeparator,
        foregroundsOnPrimary: Color = this.foregroundsOnPrimary,
        iconsPrimary: Color = this.iconsPrimary,
        iconsSecondary: Color = this.iconsSecondary,
        iconsTertiary: Color = this.iconsTertiary,
    ): RMColors = RMColors(
        accentPrimary = accentPrimary,
        backgroundsPrimary = backgroundsPrimary,
        backgroundsSecondary = backgroundsSecondary,
        backgroundsTertiary = backgroundsTertiary,
        backgroundsBottomNavigation = backgroundsBottomNavigation,
        foregroundsPrimary = foregroundsPrimary,
        foregroundsSecondary = foregroundsSecondary,
        foregroundsTertiary = foregroundsTertiary,
        foregroundsSeparator = foregroundsSeparator,
        foregroundsOnPrimary = foregroundsOnPrimary,
        iconsPrimary = iconsPrimary,
        iconsSecondary = iconsSecondary,
        iconsTertiary = iconsTertiary,
    )

    internal fun updateColorsFrom(other: RMColors) {
        accentPrimary = other.accentPrimary
        backgroundsPrimary = other.backgroundsPrimary
        backgroundsSecondary = other.backgroundsSecondary
        backgroundsTertiary = other.backgroundsTertiary
        backgroundsBottomNavigation = other.backgroundsBottomNavigation
        foregroundsPrimary = other.foregroundsPrimary
        foregroundsSecondary = other.foregroundsSecondary
        foregroundsTertiary = other.foregroundsTertiary
        foregroundsSeparator = other.foregroundsSeparator
        foregroundsOnPrimary = other.foregroundsOnPrimary
        iconsPrimary = other.iconsPrimary
        iconsSecondary = other.iconsSecondary
        iconsTertiary = other.iconsTertiary
    }
}

internal fun lightColors(): RMColors = RMColors(
    accentPrimary = Color(0xFF0000FF),
    backgroundsPrimary = Color(0xFFf4F4F9),
    backgroundsSecondary = Color(0xFFE7E7EC),
    backgroundsTertiary = Color(0xFFFFFFFF),
    backgroundsBottomNavigation = Color(0xFFFFFFFF),
    foregroundsPrimary = Color(0xFF000000),
    foregroundsSecondary = Color(0xFF666666),
    foregroundsTertiary = Color(0xFFB3B3B3),
    foregroundsSeparator = Color(0xFFF0F0F0),
    foregroundsOnPrimary = Color(0xFFFFFFFF),
    iconsPrimary = Color(0xFF000000),
    iconsSecondary = Color(0xFFB3B3B3),
    iconsTertiary = Color(0xFF0000FF),
)

internal fun darkColors(): RMColors = RMColors(
    accentPrimary = Color(0xFF9595FE),
    backgroundsPrimary = Color(0xFF181819),
    backgroundsSecondary = Color(0xFF2E2E2F),
    backgroundsTertiary = Color(0xFF474747),
    backgroundsBottomNavigation = Color(0xFF0F0F10),
    foregroundsPrimary = Color(0xFFFFFFFF),
    foregroundsSecondary = Color(0xFF8B8B8C),
    foregroundsTertiary = Color(0xFF5D5D5E),
    foregroundsSeparator = Color(0xFF474749),
    foregroundsOnPrimary = Color(0xFF000000),
    iconsPrimary = Color(0xFFFFFFFF),
    iconsSecondary = Color(0xFFB3B3B3),
    iconsTertiary = Color(0xFF9595FE),
)

internal val LocalColors = staticCompositionLocalOf { lightColors() }