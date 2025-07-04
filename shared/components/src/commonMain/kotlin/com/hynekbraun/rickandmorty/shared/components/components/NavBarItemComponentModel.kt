package com.hynekbraun.rickandmorty.shared.components.components

import com.hynekbraun.rickandmorty.shared.components.utils.DrawableRes

public data class NavBarItemComponentModel(
    val description: String,
    val icon: DrawableRes,
    val active: Boolean,
    val route: Any,
)
