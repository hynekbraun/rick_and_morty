package com.hynekbraun.rickandmorty.components.util

import androidx.compose.runtime.Composable
import app.cash.paparazzi.DeviceConfig.Companion.PIXEL_5
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import org.junit.Rule

open class PaparazziEnv(isLongTest: Boolean = false) {

    @get:Rule
    val paparazzi = Paparazzi(
        renderingMode = if (isLongTest) SessionParams.RenderingMode.V_SCROLL else SessionParams.RenderingMode.SHRINK,
    )

    internal fun createSnapshot(
        useNarrowCase: Boolean = true,
        useFontScaleTest: Boolean = false,
        content: @Composable () -> Unit,
    ) {
        paparazzi.unsafeUpdateConfig(deviceConfig = PIXEL_5.copy(softButtons = false))
        paparazzi.snapshot(name = "light") { content() }

        paparazzi.unsafeUpdateConfig(deviceConfig = PIXEL_5.copy(softButtons = false, nightMode = NightMode.NIGHT))
        paparazzi.snapshot(name = "dark") { content() }

        if (useFontScaleTest) {
            paparazzi.unsafeUpdateConfig(deviceConfig = PIXEL_5.copy(softButtons = false, fontScale = 1.6f))
            paparazzi.snapshot(name = "fontScale") { content() }
        }
        if (useNarrowCase) {
            paparazzi.unsafeUpdateConfig(deviceConfig = PIXEL_5.copy(screenWidth = 800))
            paparazzi.snapshot(name = "narrow") { content() }
        }
    }
}
