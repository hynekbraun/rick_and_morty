package com.hynekbraun.rickandmorty.components.components

import androidx.compose.runtime.Composable
import com.hynekbraun.rickandmorty.components.util.PaparazziEnv
import com.hynekbraun.rickandmorty.components.util.SnapshotPreviewColumn
import com.hynekbraun.rickandmorty.shared.components.components.CardCharacterComponentModel
import org.junit.Test

internal class CardCharacterComponentTest() : PaparazziEnv() {

    @Test
    fun test() {
        createSnapshot { CardCharacterComponentPreview() }
    }
}

@Composable
private fun CardCharacterComponentPreview() {
    SnapshotPreviewColumn {
        CardCharacterComponent(
            CardCharacterComponentModel(
                photoUrl = "",
                name = "Morty Smith",
                status = "Alive",
                showStar = true,
                id = "5",
            ),
            onClick = {},
        )
        CardCharacterComponent(
            CardCharacterComponentModel(
                photoUrl = "",
                name = "Morty Smith Morty Smith Morty Smith Morty Smith Morty Smith Morty Smith Morty Smith",
                status = "Alive Alive Alive Alive Alive Alive Alive Alive Alive Alive Alive Alive Alive",
                showStar = true,
                id = "5",
            ),
            onClick = {},
        )
    }
}