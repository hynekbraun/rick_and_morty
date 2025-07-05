package com.hynekbraun.rickandmorty.screens.charactersList

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hynekbraun.rickandmorty.screens.shared.ErrorScreen
import com.hynekbraun.rickandmorty.screens.shared.LoadingScreen
import com.hynekbraun.rickandmorty.shared.features.characterslist.CharactersListViewModel
import com.hynekbraun.rickandmorty.shared.features.characterslist.CharactersListViewState
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun CharactersListScreen(
    navigateToDetail: (String) -> Unit,
    viewModel: CharactersListViewModel = koinViewModel(),
) {
    val viewState by viewModel.state.collectAsStateWithLifecycle()

    when (viewState) {
        is CharactersListViewState.Data -> CharacterListDataScreen(
            data = viewState as CharactersListViewState.Data,
            onCharacterClick = navigateToDetail,
        )

        CharactersListViewState.Error -> ErrorScreen()
        CharactersListViewState.Loading -> LoadingScreen()
    }
}