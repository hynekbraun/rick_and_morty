package com.hynekbraun.rickandmorty.shared.components.components

import com.hynekbraun.rickandmorty.shared.components.utils.DrawableRes
import com.hynekbraun.rickandmorty.shared.components.utils.Immutable

@Immutable
public data class NavBarItemComponentModel(
    val description: String,
    val icon: DrawableRes,
    val active: Boolean,
    val route: Any,
)
