package com.hynekbraun.rickandmorty.shared.repository

import com.hynekbraun.rickandmorty.shared.network.Response
import com.hynekbraun.rickandmorty.shared.repository.models.CharacterDetailModel
import com.hynekbraun.rickandmorty.shared.repository.models.CharacterModel
import com.hynekbraun.rickandmorty.shared.repository.models.CharactersListModel
import kotlinx.coroutines.flow.Flow

public interface CharactersRepository {

    public suspend fun getCharacters(): Flow<Response<CharactersListModel>>
    public suspend fun getFavoriteCharacters(): Flow<Response<List<CharacterModel>>>

    public suspend fun getCharactersByPage(page: String): Response<CharactersListModel>

    public suspend fun getCharactersByPageAndQuery(page: String?, query: String): Response<CharactersListModel>

    public suspend fun getCharacterById(id: String): Response<CharacterDetailModel>
}
