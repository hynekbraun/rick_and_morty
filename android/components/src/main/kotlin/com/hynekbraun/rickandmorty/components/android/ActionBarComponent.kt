package com.hynekbraun.rickandmorty.components.android

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hynekbraun.rickandmorty.components.R
import com.hynekbraun.rickandmorty.components.extensions.clickableWithoutRipple
import com.hynekbraun.rickandmorty.components.theme.RMTheme
import com.hynekbraun.rickandmorty.shared.components.components.ActionBarComponentModel

@Composable
public fun ActionBarComponent(
    model: ActionBarComponentModel,
    modifier: Modifier = Modifier,
    onLeadingIconClick: (() -> Unit)? = null,
    onTrailingIconClick: (() -> Unit)? = null,
) {
    val haptic = LocalHapticFeedback.current
    Column(modifier) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            model.leadingIcon?.let {
                Image(
                    modifier = Modifier
                        .size(24.dp)
                        .clickableWithoutRipple {
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                        onLeadingIconClick?.invoke()
                    },
                    painter = painterResource(it),
                    contentDescription = null,
                )
            }
            model.title?.let {
                Text(
                    modifier = Modifier.weight(weight = 1f),
                    text = it,
                    style = RMTheme.typography.headline2,
                    color = RMTheme.colors.foregroundsPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            } ?: Spacer(modifier = Modifier.weight(weight = 1f))
            model.trailingIcon?.let {
                Image(
                    modifier = Modifier
                        .size(24.dp)
                        .clickableWithoutRipple {
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                        onTrailingIconClick?.invoke()
                    },
                    painter = painterResource(it),
                    contentDescription = null,
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.25f),
                            Color.Transparent,
                        )
                    )
                )
        )
    }
}

@Preview
@Composable
private fun ActionBarComponentPreview() = RMTheme {
    ActionBarComponent(
        modifier = Modifier.fillMaxWidth(),
        model = ActionBarComponentModel(
            title = "title title title title title title title title title title title",
            leadingIcon = R.drawable.favorites_active,
            trailingIcon = R.drawable.favorites_active,
        )
    )
}