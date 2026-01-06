package com.hynekbraun.rickandmorty.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import com.hynekbraun.rickandmorty.R
import com.hynekbraun.rickandmorty.components.components.NavBarItemComponent
import com.hynekbraun.rickandmorty.components.theme.RMTheme
import com.hynekbraun.rickandmorty.shared.components.components.NavBarItemComponentModel
import com.hynekbraun.rickandmorty.shared.navigation.Destinations

@Composable
internal fun BottomNavBar(
    currentSelectedKey: NavKey,
    onSelectKey: (NavKey) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier
            .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)),
        containerColor = RMTheme.colors.backgroundsBottomNavigation,
        tonalElevation = 2.dp,
    ) {
        Spacer(Modifier.weight(0.5f))
        navBarItems().forEach { (destination, data) ->
            NavBarItemComponent(
                modifier = Modifier
                    .weight(1f)
                    .widthIn(max = 120.dp),
                model = data.copy(active = currentSelectedKey == destination),
                onClick = {
                    if (currentSelectedKey != destination) {
                        onSelectKey(destination)
                    }
                }
            )
        }
        Spacer(Modifier.weight(0.5f))
    }
}

internal val TOP_LEVEL_DESTINATIONS: Set<NavKey> = setOf(Destinations.CharactersTab, Destinations.FavoritesTab)

@Composable
private fun navBarItems(): Map<NavKey, NavBarItemComponentModel> {
    val context = LocalContext.current
    return mapOf(
        Destinations.CharactersTab to NavBarItemComponentModel(
            description = context.getString(R.string.navigation_bar_characters),
            icon = R.drawable.maintab_characters,
            active = true,
        ),
        Destinations.FavoritesTab to NavBarItemComponentModel(
            description = context.getString(R.string.navigation_bar_characters),
            icon = R.drawable.maintab_favorites,
            active = false,
        ),
    )
}
