package com.hynekbraun.rickandmorty.shared.repository.models

public data class CharactersListModel(
    val characters: List<CharacterModel>,
    val nextPage: String?,
)
