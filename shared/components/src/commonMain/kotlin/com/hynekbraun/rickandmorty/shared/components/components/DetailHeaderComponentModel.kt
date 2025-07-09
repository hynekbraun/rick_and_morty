package com.hynekbraun.rickandmorty.shared.components.components

import com.hynekbraun.rickandmorty.shared.components.utils.Immutable

@Immutable
public data class DetailHeaderComponentModel(
    val title: String,
    val name: String,
    val imageUrl: String,
)
