package com.hynekbraun.rickandmorty

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hynekbraun.rickandmorty.components.theme.RMTheme
import com.hynekbraun.rickandmorty.navigation.NavigationRoot
import com.hynekbraun.rickandmorty.navigation.Navigator
import com.hynekbraun.rickandmorty.navigation.rememberNavigationState
import com.hynekbraun.rickandmorty.shared.navigation.Destinations
import com.hynekbraun.rickandmorty.ui.BottomNavBar
import com.hynekbraun.rickandmorty.ui.TOP_LEVEL_DESTINATIONS

@Composable
@Preview
internal fun App() {
    RMTheme {
        var bottomBarVisibility by remember { mutableStateOf(true) }

        val navigationState  = rememberNavigationState(
            startRoute = Destinations.CharactersTab,
            topLevelRoutes = TOP_LEVEL_DESTINATIONS,
        )
        val navigator = remember { Navigator(navigationState) }
        Scaffold(
            containerColor = RMTheme.colors.backgroundsPrimary,
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                AnimatedVisibility(
                    visible = bottomBarVisibility, enter = fadeIn(tween(200)), exit = fadeOut(tween(200))
                ) {
                    BottomNavBar(
                        currentSelectedKey = navigationState.topLevelRoute,
                        onSelectKey = navigator::navigate
                    )
                }
            }
        ) { padding ->
            NavigationRoot(
                navigator = navigator,
                navigationState = navigationState,
                onBottomBarVisibilityChange = { bottomBarVisibility = it },
                modifier = Modifier.padding(padding)
            )
        }
    }
}
