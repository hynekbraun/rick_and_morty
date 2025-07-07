package com.hynekbraun.rickandmorty.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.hynekbraun.rickandmorty.screens.characterDetail.CharacterDetailScreen
import com.hynekbraun.rickandmorty.screens.charactersList.CharactersListScreen
import com.hynekbraun.rickandmorty.screens.favoritesList.FavoritesListScreen
import com.hynekbraun.rickandmorty.screens.search.SearchScreen
import com.hynekbraun.rickandmorty.shared.features.characterdetail.CharacterDetailViewModel
import com.hynekbraun.rickandmorty.shared.navigation.Destinations
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
internal fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onBottomBarVisibilityChange: (Boolean) -> Unit = {},
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Destinations.Maintab.Characters,
        enterTransition = { fadeIn(tween(400)) },
        exitTransition = { fadeOut(tween(400)) },
    ) {

        navigation<Destinations.Maintab.Characters>(
            startDestination = Destinations.Characters
        ) {

            charactersList(
                navController = navController,
                onBottomBarVisibilityChange = onBottomBarVisibilityChange
            )

            characterDetail(
                navController = navController,
                onBottomBarVisibilityChange = onBottomBarVisibilityChange
            )

            search(
                navController = navController,
                onBottomBarVisibilityChange = onBottomBarVisibilityChange
            )
        }

        navigation<Destinations.Maintab.Favorites>(
            startDestination = Destinations.Favorites,
        ) {
            favoritesList(
                navController = navController,
                onBottomBarVisibilityChange = onBottomBarVisibilityChange
            )

            characterDetail(
                navController = navController,
                onBottomBarVisibilityChange = onBottomBarVisibilityChange
            )
        }
    }

}

private fun NavGraphBuilder.charactersList(
    navController: NavController,
    onBottomBarVisibilityChange: (Boolean) -> Unit = {},
) {
    composable<Destinations.Characters> { backStackEntry ->
        onBottomBarVisibilityChange(true)
        CharactersListScreen(
            navigateToDetail = { id ->
                navController.navigate(Destinations.Detail(id))
            },
            navigateToSearch = remember { { navController.navigate(Destinations.Search) } }
        )
    }
}

private fun NavGraphBuilder.favoritesList(
    navController: NavController,
    onBottomBarVisibilityChange: (Boolean) -> Unit = {},
) {
    composable<Destinations.Favorites> { backStackEntry ->
        onBottomBarVisibilityChange(true)
        FavoritesListScreen(
            navigateToDetail = { id ->
                navController.navigate(Destinations.Detail(id))
            },
        )
    }
}

private fun NavGraphBuilder.search(
    navController: NavController,
    onBottomBarVisibilityChange: (Boolean) -> Unit = {},
) {
    composable<Destinations.Search> { backStackEntry ->
        onBottomBarVisibilityChange(false)
        SearchScreen(
            navigateBack = remember { { navController.navigateUp() } },
            navigateToCharacterDetail = { id ->
                navController.navigate(Destinations.Detail(id))
            },
        )
    }
}

private fun NavGraphBuilder.characterDetail(
    navController: NavController,
    onBottomBarVisibilityChange: (Boolean) -> Unit = {},
) {
    composable<Destinations.Detail> { backStackEntry ->

        val destination: Destinations.Detail = backStackEntry.toRoute()
        val viewModel = koinViewModel<CharacterDetailViewModel>(
            parameters = { parametersOf(destination.characterId) }
        )
        onBottomBarVisibilityChange(false)
        CharacterDetailScreen(viewModel, navigateBack = remember { { navController.popBackStack() } })
    }
}
