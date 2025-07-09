package com.hynekbraun.rickandmorty.shared.components.components

import com.hynekbraun.rickandmorty.shared.components.utils.Immutable

@Immutable
public data class InformationComponentModel(
    val title: String,
    val value: String,
)
