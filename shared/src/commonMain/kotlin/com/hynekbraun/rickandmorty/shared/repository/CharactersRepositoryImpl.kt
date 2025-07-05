package com.hynekbraun.rickandmorty.shared.repository

import com.hynekbraun.rickandmorty.shared.network.Response
import com.hynekbraun.rickandmorty.shared.repository.api.CharactersApi
import com.hynekbraun.rickandmorty.shared.repository.api.models.CharacterDetailApiModel
import com.hynekbraun.rickandmorty.shared.repository.api.models.CharactersApiModel
import com.hynekbraun.rickandmorty.shared.repository.api.models.toDomainModel
import com.hynekbraun.rickandmorty.shared.repository.models.CharacterDetailModel
import com.hynekbraun.rickandmorty.shared.repository.models.CharacterModel

internal class CharactersRepositoryImpl(
    private val api: CharactersApi,
) : CharactersRepository {
    override suspend fun getCharacters(): Response<List<CharacterModel>> {
        return when (val response = api.getCharacters()) {
            is Response.Error<CharactersApiModel> -> return Response.Error(response.message)
            is Response.Success<CharactersApiModel> -> Response.Success(response.data.characters.map { it.toDomainModel() })
        }
    }

    override suspend fun getCharacterById(id: String): Response<CharacterDetailModel> {
        return when (val response = api.getCharacterById(id)) {
            is Response.Error<CharacterDetailApiModel> -> return Response.Error(response.message)
            is Response.Success<CharacterDetailApiModel> -> Response.Success(response.data.toDomainModel())
        }
    }
}
