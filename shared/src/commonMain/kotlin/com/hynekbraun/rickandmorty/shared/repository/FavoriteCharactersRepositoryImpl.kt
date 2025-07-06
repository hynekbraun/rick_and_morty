package com.hynekbraun.rickandmorty.shared.repository

import com.hynekbraun.rickandmorty.shared.database.FavoriteCharacterEntity
import com.hynekbraun.rickandmorty.shared.database.FavoriteCharactersDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class FavoriteCharactersRepositoryImpl(
    private val favoriteCharactersDao: FavoriteCharactersDao,
) : FavoriteCharactersRepository {

    override fun getFavoriteCharacters(): Flow<List<String>> {
        return favoriteCharactersDao.getAll().map { it.map { it.id } }
    }

    override suspend fun getFavoriteCharacterById(id: String): String? {
        return favoriteCharactersDao.findById(id)?.id
    }

    override suspend fun toggle(characterId: String, isFavorite: Boolean) {
        if (isFavorite) {
            favoriteCharactersDao.delete(FavoriteCharacterEntity(characterId))
        } else {
            favoriteCharactersDao.insert(FavoriteCharacterEntity(characterId))
        }
    }
}
