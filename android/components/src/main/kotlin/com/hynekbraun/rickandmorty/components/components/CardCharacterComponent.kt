package com.hynekbraun.rickandmorty.components.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hynekbraun.rickandmorty.components.R
import com.hynekbraun.rickandmorty.components.theme.RMTheme
import com.hynekbraun.rickandmorty.components.util.CachedAsyncImage
import com.hynekbraun.rickandmorty.shared.components.components.CardCharacterComponentModel

@Composable
public fun CardCharacterComponent(
    model: CardCharacterComponentModel,
    onClick: (String) -> Unit,
    onHold: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .combinedClickable(
                onClick = remember { { onClick(model.id) } },
                onLongClick = remember { { onHold(model.photoUrl) } },
                ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = RMTheme.colors.backgroundsTertiary),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.Top,
        ) {
            CachedAsyncImage(
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(8.dp)),
                model = model.photoUrl,
                fallback = painterResource(R.drawable.icon_warning)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        modifier = Modifier.weight(1f, false),
                        text = model.name,
                        style = RMTheme.typography.headline3,
                        color = RMTheme.colors.foregroundsPrimary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    if (model.showStar) {
                        Image(
                            modifier = Modifier.size(16.dp),
                            painter = painterResource(R.drawable.favorites_active),
                            contentDescription = "favorite",
                        )
                    }
                }
                Text(
                    text = model.status,
                    style = RMTheme.typography.paragraphSmall,
                    color = RMTheme.colors.foregroundsSecondary,
                )
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CardCharacterComponentPreview() = RMTheme {
    Column(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        CardCharacterComponent(
            modifier = Modifier.fillMaxWidth(),
            model = CardCharacterComponentModel(
                photoUrl = "",
                name = "Morty Smith",
                status = "Alive",
                showStar = true,
                id = "5",
            ),
            onClick = {},
            onHold = {},
        )
        CardCharacterComponent(
            modifier = Modifier.fillMaxWidth(),
            model = CardCharacterComponentModel(
                photoUrl = "",
                name = "Morty Smith Morty Smith Morty Smith Morty Smith Morty Smith",
                status = "Alive",
                showStar = true,
                id = "5",
            ),
            onClick = {},
            onHold = {},
        )
    }
}