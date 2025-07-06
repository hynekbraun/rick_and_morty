package com.hynekbraun.rickandmorty.screens.characterDetail

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hynekbraun.rickandmorty.components.android.ActionBarComponent
import com.hynekbraun.rickandmorty.screens.shared.ErrorScreen
import com.hynekbraun.rickandmorty.screens.shared.LoadingScreen
import com.hynekbraun.rickandmorty.shared.features.characterdetail.CharacterDetailViewModel
import com.hynekbraun.rickandmorty.shared.features.characterdetail.CharacterDetailViewState

@Composable
internal fun CharacterDetailScreen(
    viewModel: CharacterDetailViewModel,
    navigateBack: () -> Unit,
) {

    val viewState by viewModel.state.collectAsStateWithLifecycle()

    val actionBarState by viewModel.actionBarState.collectAsStateWithLifecycle()


    Column {
        ActionBarComponent(
            model = actionBarState,
            onLeadingIconClick = navigateBack,
            onTrailingIconClick = viewModel::toggleFavorite
        )
        when (viewState) {
            is CharacterDetailViewState.Data -> CharacterDetailDataScreen(
                data = viewState as CharacterDetailViewState.Data,
            )

            is CharacterDetailViewState.Error -> ErrorScreen()
            is CharacterDetailViewState.Loading -> LoadingScreen()
        }
    }
}
