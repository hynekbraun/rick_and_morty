package com.hynekbraun.rickandmorty.ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.hynekbraun.rickandmorty.R
import com.hynekbraun.rickandmorty.components.components.NavBarItemComponent
import com.hynekbraun.rickandmorty.components.components.NavBarItemComponentModel
import com.hynekbraun.rickandmorty.components.theme.RMTheme
import com.hynekbraun.rickandmorty.shared.navigation.Destinations

@Composable
internal fun BottomNavBar(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier.clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)),
        tonalElevation = 2.dp,
        containerColor = RMTheme.colors.backgroundsSecondary
    ) {
        val navBackStackEntry: NavBackStackEntry? by navController.currentBackStackEntryAsState()
        val currentDestination: NavDestination? = navBackStackEntry?.destination

        navBarItems().forEachIndexed { index, item ->
            NavBarItemComponent(
                modifier = Modifier.weight(1f),
                model = item.copy(active = currentDestination?.hierarchy?.any { it.hasRoute(item.route::class) } == true),
                onClick = { navController.navigate(item.route) },
            )
        }
    }
}

@Composable
private fun navBarItems(): List<NavBarItemComponentModel> {
    return listOf(
        NavBarItemComponentModel(
            description = "Characters",
            icon = R.drawable.maintab_characters,
            active = true,
            route = Destinations.Maintab.Characters,
        ),
        NavBarItemComponentModel(
            description = "Favorites",
            icon = R.drawable.maintab_favorites,
            active = false,
            route = Destinations.Maintab.Favorites,
        ),
    )
}
