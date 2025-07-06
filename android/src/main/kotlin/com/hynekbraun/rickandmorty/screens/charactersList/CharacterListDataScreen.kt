package com.hynekbraun.rickandmorty.screens.charactersList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hynekbraun.rickandmorty.components.components.CardCharacterComponent
import com.hynekbraun.rickandmorty.components.components.NextPageComponent
import com.hynekbraun.rickandmorty.shared.features.characterslist.CharactersListViewState

@Composable
internal fun CharacterListDataScreen(
    data: CharactersListViewState.Data,
    onCharacterClick: (String) -> Unit,
    loadNextPage: () -> Unit,
    modifier: Modifier = Modifier,
) {

    LazyColumn(
        modifier = modifier.padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item() {
            Spacer(modifier = Modifier.height(2.dp))
        }
        items(items = data.characters, key = { it.id }) {
            CardCharacterComponent(
                modifier = Modifier.fillMaxWidth(),
                model = it,
                onClick = onCharacterClick,
            )
        }
        data.nextPage?.let {
            item {
                NextPageComponent(Modifier.fillMaxWidth())
                loadNextPage()
            }
        }
    }
}
