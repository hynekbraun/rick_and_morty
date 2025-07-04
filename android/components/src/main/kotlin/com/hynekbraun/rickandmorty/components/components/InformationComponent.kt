package com.hynekbraun.rickandmorty.components.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hynekbraun.rickandmorty.components.theme.RMTheme
import com.hynekbraun.rickandmorty.shared.components.components.InformationComponentModel

@Composable
public fun InformationComponent(
    model: InformationComponentModel,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier,
            text = model.title,
            style = RMTheme.typography.paragraphSmall,
            color = RMTheme.colors.foregroundsSecondary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            text = model.value,
            style = RMTheme.typography.headline3,
            color = RMTheme.colors.foregroundsPrimary,
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun InformationComponentPreview() = RMTheme {
    Column(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .background(RMTheme.colors.backgroundsPrimary),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        InformationComponent(
            model = InformationComponentModel(
                title = "Status",
                value = "Alive",
            )
        )
    }
}
