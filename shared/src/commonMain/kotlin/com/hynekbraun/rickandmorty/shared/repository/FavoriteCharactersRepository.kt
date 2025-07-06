package com.hynekbraun.rickandmorty.shared.repository

import kotlinx.coroutines.flow.Flow

public interface FavoriteCharactersRepository {

    public fun getFavoriteCharacters(): Flow<List<String>>

    public suspend fun getFavoriteCharacterById(id: String): String?

    public suspend fun toggle(characterId: String, isFavorite: Boolean)
}
