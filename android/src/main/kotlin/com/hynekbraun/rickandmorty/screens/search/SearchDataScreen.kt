package com.hynekbraun.rickandmorty.screens.search

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hynekbraun.rickandmorty.components.components.CardCharacterSearchComponent
import com.hynekbraun.rickandmorty.components.components.NextPageComponent
import com.hynekbraun.rickandmorty.shared.features.search.SearchViewState

@Composable
internal fun SearchDataScreen(
    data: SearchViewState.Data,
    onCardClick: (String) -> Unit,
    loadNextPage: () -> Unit,
    modifier: Modifier = Modifier,
) {

    LazyColumn(modifier = modifier) {
        items(items = data.characters, key = { it.id }) {
            CardCharacterSearchComponent(
                model = it,
                onClick = onCardClick,
            )
        }

        data.nextPage?.let {
            item {
                NextPageComponent()
                loadNextPage()
            }
        }
    }
}
