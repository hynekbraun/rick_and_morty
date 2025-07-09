package com.hynekbraun.rickandmorty.shared.components.components

import com.hynekbraun.rickandmorty.shared.components.utils.DrawableRes
import com.hynekbraun.rickandmorty.shared.components.utils.Immutable

@Immutable
public data class ActionBarComponentModel(
    val title: String?,
    val leadingIcon: DrawableRes?,
    val trailingIcon: DrawableRes?,
)