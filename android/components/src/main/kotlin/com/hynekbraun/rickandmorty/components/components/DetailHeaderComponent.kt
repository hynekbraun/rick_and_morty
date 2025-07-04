package com.hynekbraun.rickandmorty.components.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hynekbraun.rickandmorty.components.R
import com.hynekbraun.rickandmorty.components.theme.RMTheme
import com.hynekbraun.rickandmorty.components.util.CachedAsyncImage
import com.hynekbraun.rickandmorty.shared.components.components.DetailHeaderComponentModel

@Composable
public fun DetailHeaderComponent(
    model: DetailHeaderComponentModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            CachedAsyncImage(
                modifier = Modifier
                    .size(140.dp)
                    .clip(RoundedCornerShape(8.dp)),
                model = model.imageUrl,
                fallback = painterResource(R.drawable.favorites_active)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Text(
                    modifier = Modifier,
                    text = model.title,
                    style = RMTheme.typography.paragraphMedium,
                    color = RMTheme.colors.foregroundsSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = model.name,
                    style = RMTheme.typography.headline2,
                    color = RMTheme.colors.foregroundsPrimary,
                )
            }
        }
        HorizontalDivider(Modifier.fillMaxWidth(), thickness = 1.dp, color = RMTheme.colors.foregroundsSecondary)
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DetailHeaderComponentPreview() = RMTheme {
    Column(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .background(RMTheme.colors.backgroundsPrimary),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        DetailHeaderComponent(
            model = DetailHeaderComponentModel(
                title = "Name",
                name = "Ants in my Eyes Johnson",
                imageUrl = "",
            )
        )
    }
}
