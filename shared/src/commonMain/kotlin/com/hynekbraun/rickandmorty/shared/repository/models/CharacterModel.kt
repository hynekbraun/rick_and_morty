package com.hynekbraun.rickandmorty.shared.repository.models

public data class CharacterModel(
    val photoUrl: String,
    val id: String,
    val name: String,
    val status: String,
    val isFavorite: Boolean,
)
