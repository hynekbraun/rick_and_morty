package com.hynekbraun.rickandmorty.components.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hynekbraun.rickandmorty.components.extensions.clickableWithoutRipple
import com.hynekbraun.rickandmorty.components.theme.RMTheme

// TODO move to KMP
public data class NavBarItemComponentModel(
    val description: String,
    @DrawableRes val icon: Int,
    val active: Boolean,
    val route: Any,
)

@Composable
public fun NavBarItemComponent(
    model: NavBarItemComponentModel,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {

    val contentColor by animateColorAsState(
        targetValue = if (model.active) RMTheme.colors.accentPrimary else RMTheme.colors.iconsSecondary,
        animationSpec = tween(durationMillis = 200)
    )
    Column(
        modifier = modifier.clickableWithoutRipple(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(model.icon),
            contentDescription = "mainTab",
            tint = contentColor,
        )
        Text(
            text = model.description,
            color = contentColor,
            style = RMTheme.typography.bottomNavigation,
        )

    }
}
