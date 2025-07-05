package com.hynekbraun.rickandmorty.screens.characterDetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hynekbraun.rickandmorty.screens.charactersList.CharacterListDataScreen
import com.hynekbraun.rickandmorty.screens.shared.ErrorScreen
import com.hynekbraun.rickandmorty.screens.shared.LoadingScreen
import com.hynekbraun.rickandmorty.shared.features.characterdetail.CharacterDetailViewModel
import com.hynekbraun.rickandmorty.shared.features.characterdetail.CharacterDetailViewState
import com.hynekbraun.rickandmorty.shared.features.characterslist.CharactersListViewState
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun CharacterDetailScreen(
    viewModel: CharacterDetailViewModel,
) {

    val viewState by viewModel.state.collectAsStateWithLifecycle()

    when (viewState) {
        is CharacterDetailViewState.Data -> CharacterDetailDataScreen(
            data = viewState as CharacterDetailViewState.Data,
        )

        CharacterDetailViewState.Error -> ErrorScreen()
        CharacterDetailViewState.Loading -> LoadingScreen()
    }
}
