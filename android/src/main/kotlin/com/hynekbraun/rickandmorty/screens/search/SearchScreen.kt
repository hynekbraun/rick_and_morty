package com.hynekbraun.rickandmorty.screens.search

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hynekbraun.rickandmorty.R
import com.hynekbraun.rickandmorty.components.android.ActionBarSearchComponent
import com.hynekbraun.rickandmorty.screens.shared.ErrorScreen
import com.hynekbraun.rickandmorty.shared.features.search.SearchViewModel
import com.hynekbraun.rickandmorty.shared.features.search.SearchViewState
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun SearchScreen(
    navigateBack: () -> Unit,
    navigateToCharacterDetail: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = koinViewModel<SearchViewModel>(),
) {

    val context = LocalContext.current

    val state by viewModel.state.collectAsStateWithLifecycle()
    val query by viewModel.queryState.collectAsStateWithLifecycle()

    Column(modifier) {
        ActionBarSearchComponent(
            text = query,
            onTextChanged = viewModel::changeQuery,
            onDeleteClicked = viewModel::clearQuery,
            onLeadingIconClick = navigateBack,
            prompt = context.getString(R.string.search_prompt),
        )

        when (state) {
            is SearchViewState.Data -> SearchDataScreen(
                data = state as SearchViewState.Data,
                loadNextPage = viewModel::getNextPage,
                onCardClick = navigateToCharacterDetail,
            )

            SearchViewState.Error -> ErrorScreen()
            SearchViewState.Initial -> Unit
        }
    }
}
