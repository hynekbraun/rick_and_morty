package com.hynekbraun.rickandmorty.screens.favoritesList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hynekbraun.rickandmorty.R
import com.hynekbraun.rickandmorty.components.android.ActionBarComponent
import com.hynekbraun.rickandmorty.screens.shared.ErrorScreen
import com.hynekbraun.rickandmorty.screens.shared.LoadingScreen
import com.hynekbraun.rickandmorty.shared.components.components.ActionBarComponentModel
import com.hynekbraun.rickandmorty.shared.features.characterslist.CharactersListViewModel
import com.hynekbraun.rickandmorty.shared.features.favoriteslist.FavoritesListViewModel
import com.hynekbraun.rickandmorty.shared.features.favoriteslist.FavoritesListViewState
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun FavoritesListScreen(
    navigateToDetail: (String) -> Unit,
    viewModel: FavoritesListViewModel = koinViewModel(),
) {

    val context = LocalContext.current

    val viewState by viewModel.state.collectAsStateWithLifecycle()
    Column(
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        ActionBarComponent(
            model = ActionBarComponentModel(
                title = context.getString(R.string.favorites_title),
                leadingIcon = null,
                trailingIcon = null,
            ),
        )
        when (viewState) {
            is FavoritesListViewState.Data -> FavoritesListDataScreen(
                data = viewState as FavoritesListViewState.Data,
                onCharacterClick = navigateToDetail
            )

            FavoritesListViewState.Error -> ErrorScreen()
            FavoritesListViewState.Loading -> LoadingScreen()
        }
    }
}
