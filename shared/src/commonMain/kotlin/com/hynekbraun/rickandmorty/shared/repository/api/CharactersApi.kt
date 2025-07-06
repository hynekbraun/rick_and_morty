package com.hynekbraun.rickandmorty.shared.repository.api

import com.hynekbraun.rickandmorty.shared.network.Response
import com.hynekbraun.rickandmorty.shared.repository.api.models.CharacterDetailApiModel
import com.hynekbraun.rickandmorty.shared.repository.api.models.CharactersApiModel

internal interface CharactersApi {

    suspend fun getCharacters(): Response<CharactersApiModel>

    suspend fun getCharactersByPage(url: String): Response<CharactersApiModel>

    suspend fun getCharacterById(id: String): Response<CharacterDetailApiModel>
}