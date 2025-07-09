package com.hynekbraun.rickandmorty.shared.components.components

import com.hynekbraun.rickandmorty.shared.components.utils.Immutable

@Immutable
public data class CardCharacterComponentModel(
    val photoUrl: String,
    val name: String,
    val status: String,
    val showStar: Boolean,
    val id: String,
)
