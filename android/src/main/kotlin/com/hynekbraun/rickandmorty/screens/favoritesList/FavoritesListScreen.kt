package com.hynekbraun.rickandmorty.screens.favoritesList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.hynekbraun.rickandmorty.R
import com.hynekbraun.rickandmorty.components.android.ActionBarComponent
import com.hynekbraun.rickandmorty.shared.components.components.ActionBarComponentModel

@Composable
internal fun FavoritesListScreen(modifier: Modifier = Modifier) {

    val context = LocalContext.current
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
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Favorites List")
        }
    }
}
