package com.hynekbraun.rickandmorty.screens.charactersList

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hynekbraun.rickandmorty.components.R as CUIR
import com.hynekbraun.rickandmorty.R
import com.hynekbraun.rickandmorty.components.android.ActionBarComponent
import com.hynekbraun.rickandmorty.screens.shared.ErrorScreen
import com.hynekbraun.rickandmorty.screens.shared.LoadingScreen
import com.hynekbraun.rickandmorty.shared.components.components.ActionBarComponentModel
import com.hynekbraun.rickandmorty.shared.features.characterslist.CharactersListViewModel
import com.hynekbraun.rickandmorty.shared.features.characterslist.CharactersListViewState
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun CharactersListScreen(
    navigateToDetail: (String) -> Unit,
    navigateToPictureDialog: (String) -> Unit,
    navigateToSearch: () -> Unit,
    viewModel: CharactersListViewModel = koinViewModel(),
) {
    val viewState by viewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current
    Column(
    ) {
        ActionBarComponent(
            model = ActionBarComponentModel(
                title = context.getString(R.string.characters_list_action_bar_title),
                leadingIcon = null,
                trailingIcon = CUIR.drawable.search,
            ),
            onTrailingIconClick = navigateToSearch,
        )
        when (viewState) {
            is CharactersListViewState.Data -> CharacterListDataScreen(
                data = viewState as CharactersListViewState.Data,
                onCharacterClick = navigateToDetail,
                onCharacterHold = navigateToPictureDialog,
                loadNextPage = viewModel::getNextPage,
            )

            CharactersListViewState.Error -> ErrorScreen()
            CharactersListViewState.Loading -> LoadingScreen()
        }
    }
}