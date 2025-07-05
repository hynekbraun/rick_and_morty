package com.hynekbraun.rickandmorty.shared.repository.models

public data class CharacterDetailModel(
    val photoUrl: String,
    val id: String,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: String,
    val location: String,
)
