package com.hynekbraun.rickandmorty.shared.api

import com.hynekbraun.rickandmorty.shared.network.NetworkExecutor
import com.hynekbraun.rickandmorty.shared.network.Response
import com.hynekbraun.rickandmorty.shared.network.get
import com.hynekbraun.rickandmorty.shared.network.getList
import com.hynekbraun.rickandmorty.shared.api.models.CharacterDetailApiModel
import com.hynekbraun.rickandmorty.shared.api.models.CharactersApiModel

internal class CharactersApiImpl(
    private val networkExecutor: NetworkExecutor,
) : CharactersApi {
    override suspend fun getCharacters(): Response<CharactersApiModel> =
        networkExecutor.get<CharactersApiModel>(CHARACTERS_URL)

    override suspend fun getCharactersByIds(ids: List<String>): Response<List<CharactersApiModel.Results>> {
        return networkExecutor.getList<CharactersApiModel.Results>("$BASE_URL$DELIMITER${ids.joinToString()}")
    }

    override suspend fun getCharactersByPage(url: String): Response<CharactersApiModel> =
        networkExecutor.get<CharactersApiModel>(url)

    override suspend fun getCharactersByPageAndQuery(url: String?, query: String): Response<CharactersApiModel> {
        val safeUrl = if (url == null) "$CHARACTERS_URL$NAME_FILTER$query" else "$url$NAME_FILTER$query"
        return networkExecutor.get<CharactersApiModel>(safeUrl)
    }

    override suspend fun getCharacterById(id: String): Response<CharacterDetailApiModel> =
        networkExecutor.get<CharacterDetailApiModel>("$BASE_URL$DELIMITER$id")

    companion object {
        internal const val DELIMITER: String = "/"
        internal const val BASE_URL: String = "https://rickandmortyapi.com/api/character"
        internal const val CHARACTERS_URL: String = "$BASE_URL?page=1"
        internal const val NAME_FILTER: String = "&name="
    }
}
