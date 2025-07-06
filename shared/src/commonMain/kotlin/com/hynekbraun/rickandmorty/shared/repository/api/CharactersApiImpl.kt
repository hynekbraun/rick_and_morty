package com.hynekbraun.rickandmorty.shared.repository.api

import com.hynekbraun.rickandmorty.shared.network.NetworkExecutor
import com.hynekbraun.rickandmorty.shared.network.Response
import com.hynekbraun.rickandmorty.shared.network.get
import com.hynekbraun.rickandmorty.shared.repository.api.models.CharacterDetailApiModel
import com.hynekbraun.rickandmorty.shared.repository.api.models.CharactersApiModel

internal class CharactersApiImpl(
    private val networkExecutor: NetworkExecutor,
) : CharactersApi {
    override suspend fun getCharacters(): Response<CharactersApiModel> =
        networkExecutor.get<CharactersApiModel>(CHARACTERS_URL)

    override suspend fun getCharactersByPage(url: String): Response<CharactersApiModel> =
        networkExecutor.get<CharactersApiModel>(url)

    override suspend fun getCharacterById(id: String): Response<CharacterDetailApiModel> =
        networkExecutor.get<CharacterDetailApiModel>("$BASE_URL$DELIMITER$id")
}

private const val DELIMITER: String = "/"
private const val BASE_URL: String = "https://rickandmortyapi.com/api/character"
private const val CHARACTERS_URL: String = "$BASE_URL?page=1"