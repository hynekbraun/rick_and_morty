package com.hynekbraun.rickandmorty.screens.characterDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.hynekbraun.rickandmorty.components.components.DetailHeaderComponent
import com.hynekbraun.rickandmorty.components.components.InformationComponent
import com.hynekbraun.rickandmorty.components.theme.RMTheme
import com.hynekbraun.rickandmorty.shared.features.characterdetail.CharacterDetailViewState

@Composable
internal fun CharacterDetailDataScreen(
    data: CharacterDetailViewState.Data,
    modifier: Modifier = Modifier,
) {

    LazyColumn(
        modifier = modifier
            .padding(horizontal = 8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(RMTheme.colors.backgroundsSecondary)
            .padding(top = 16.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item {
            DetailHeaderComponent(data.header)
        }
        items(data.info, key = { it.title }) {
            InformationComponent(
                modifier = Modifier.padding(horizontal = 16.dp),
                model = it,
                )
        }
    }
}
