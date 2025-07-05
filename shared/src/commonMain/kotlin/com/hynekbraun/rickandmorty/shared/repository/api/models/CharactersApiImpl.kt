package com.hynekbraun.rickandmorty.shared.repository.api.models

import com.hynekbraun.rickandmorty.shared.network.NetworkExecutor
import com.hynekbraun.rickandmorty.shared.network.Response
import com.hynekbraun.rickandmorty.shared.network.get
import com.hynekbraun.rickandmorty.shared.repository.api.CharactersApi

internal class CharactersApiImpl(
    private val networkExecutor: NetworkExecutor,
) : CharactersApi {
    override suspend fun getCharacters(): Response<CharactersApiModel> =
        networkExecutor.get<CharactersApiModel>(CHARACTERS_URL)
}

private const val BASE_URL: String = "https://rickandmortyapi.com/api/character"
private const val CHARACTERS_URL: String = "$BASE_URL?page=1"