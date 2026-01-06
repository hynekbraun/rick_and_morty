package com.hynekbraun.rickandmorty.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.scene.DialogSceneStrategy
import androidx.navigation3.ui.NavDisplay
import com.hynekbraun.rickandmorty.screens.characterDetail.CharacterDetailScreen
import com.hynekbraun.rickandmorty.screens.charactersList.CharactersListScreen
import com.hynekbraun.rickandmorty.screens.favoritesList.FavoritesListScreen
import com.hynekbraun.rickandmorty.screens.pictureDialog.PictureDialog
import com.hynekbraun.rickandmorty.screens.search.SearchScreen
import com.hynekbraun.rickandmorty.shared.features.characterdetail.CharacterDetailViewModel
import com.hynekbraun.rickandmorty.shared.navigation.Destinations
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
internal fun NavigationRoot(
    navigator: Navigator,
    navigationState: NavigationState,
    onBottomBarVisibilityChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavDisplay(
        modifier = modifier,
        onBack = navigator::goBack,
        sceneStrategy = DialogSceneStrategy(),
        entries = navigationState.toEntries(
            entryProvider {
                entry<Destinations.CharactersTab> {
                    onBottomBarVisibilityChange(true)
                    CharactersListScreen(
                        navigateToDetail = { navigator.navigate(Destinations.Detail(it)) },
                        navigateToSearch = { navigator.navigate(Destinations.Search) },
                        navigateToPictureDialog = { navigator.navigate(Destinations.PictureDialog(it)) }
                    )
                }

                entry<Destinations.FavoritesTab> {
                    onBottomBarVisibilityChange(true)
                    FavoritesListScreen(
                        navigateToDetail = { navigator.navigate(Destinations.Detail(it)) },
                        navigateToPictureDialog = { navigator.navigate(Destinations.PictureDialog(it)) },
                        )
                }

                entry<Destinations.Detail> { key ->
                    onBottomBarVisibilityChange(false)
                    CharacterDetailScreen(
                        navigateBack = navigator::goBack,
                        viewModel = koinViewModel<CharacterDetailViewModel>(parameters = { parametersOf(key.characterId) })
                    )
                }

                entry<Destinations.Search> {
                    onBottomBarVisibilityChange(false)
                    SearchScreen(
                        navigateBack = navigator::goBack,
                        navigateToCharacterDetail = { navigator.navigate(Destinations.Detail(it)) }
                    )
                }

                entry<Destinations.PictureDialog>(
                    metadata = DialogSceneStrategy.dialog()
                ) { key ->
                    PictureDialog(pictureUrl = key.photoUrl)
                }
            }
        )
    )
}
