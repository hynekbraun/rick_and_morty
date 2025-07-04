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
import androidx.navigation.compose.rememberNavController
import com.hynekbraun.rickandmorty.components.theme.RMTheme
import com.hynekbraun.rickandmorty.navigation.NavGraph
import com.hynekbraun.rickandmorty.ui.BottomNavBar

@Composable
@Preview
internal fun App() {
    RMTheme {
        val navController = rememberNavController()

        var bottomBarVisibility by remember { mutableStateOf(true) }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                AnimatedVisibility(
                    visible = bottomBarVisibility, enter = fadeIn(tween(200)), exit = fadeOut(tween(200))
                ) {
                    BottomNavBar(navController)
                }
            }
        ) { padding ->
            NavGraph(
                navController = navController,
                modifier = Modifier.padding(padding)
            ) { bottomBarVisible ->
                bottomBarVisibility = bottomBarVisible
            }
        }
    }
}