package com.hynekbraun.rickandmorty.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hynekbraun.rickandmorty.screens.characterDetail.CharacterDetailScreen
import com.hynekbraun.rickandmorty.screens.charactersList.CharactersListScreen
import com.hynekbraun.rickandmorty.screens.favoritesList.FavoritesListScreen
import com.hynekbraun.rickandmorty.shared.navigation.Destinations

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
        enterTransition = { fadeIn(tween(400))  },
        exitTransition = { fadeOut(tween(400))  },
    ) {

        charactersList(
            navController = navController,
            onBottomBarVisibilityChange = onBottomBarVisibilityChange
        )

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

private fun NavGraphBuilder.charactersList(
    navController: NavController,
    onBottomBarVisibilityChange: (Boolean) -> Unit = {},
) {
    composable<Destinations.Maintab.Characters> { backStackEntry ->
        onBottomBarVisibilityChange(true)
        CharactersListScreen(
            navigateToDetail = {
                navController.navigate(Destinations.Detail)
            }
        )
    }
}

private fun NavGraphBuilder.favoritesList(
    navController: NavController,
    onBottomBarVisibilityChange: (Boolean) -> Unit = {},
) {
    composable<Destinations.Maintab.Favorites> { backStackEntry ->
        onBottomBarVisibilityChange(true)
        FavoritesListScreen()
    }
}

private fun NavGraphBuilder.characterDetail(
    navController: NavController,
    onBottomBarVisibilityChange: (Boolean) -> Unit = {},
) {
    composable<Destinations.Detail> { backStackEntry ->
        onBottomBarVisibilityChange(false)
        CharacterDetailScreen()
    }
}
