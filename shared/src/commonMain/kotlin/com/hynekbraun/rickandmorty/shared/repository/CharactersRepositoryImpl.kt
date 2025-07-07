package com.hynekbraun.rickandmorty.shared.repository

import com.hynekbraun.rickandmorty.shared.database.FavoriteCharactersDao
import com.hynekbraun.rickandmorty.shared.network.Response
import com.hynekbraun.rickandmorty.shared.repository.api.CharactersApi
import com.hynekbraun.rickandmorty.shared.repository.api.models.CharacterDetailApiModel
import com.hynekbraun.rickandmorty.shared.repository.api.models.CharactersApiModel
import com.hynekbraun.rickandmorty.shared.repository.api.models.toDomainModel
import com.hynekbraun.rickandmorty.shared.repository.models.CharacterDetailModel
import com.hynekbraun.rickandmorty.shared.repository.models.CharacterModel
import com.hynekbraun.rickandmorty.shared.repository.models.CharactersListModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

internal class CharactersRepositoryImpl(
    private val api: CharactersApi,
    private val favoritesDao: FavoriteCharactersDao,
) : CharactersRepository {
    override suspend fun getCharacters(): Flow<Response<CharactersListModel>> =
        favoritesDao.getAll().map { favorites ->
            when (val response = api.getCharacters()) {
                is Response.Error<CharactersApiModel> -> Response.Error(response.message)
                is Response.Success<CharactersApiModel> -> Response.Success(response.data.toDomainModel(favorites.map { it.id }))
            }
        }

    override suspend fun getFavoriteCharacters(): Flow<Response<List<CharacterModel>>> =
        favoritesDao.getAll().map { favorites ->
            when (val response = api.getCharactersByIds(favorites.map { it.id })) {
                is Response.Error<List<CharactersApiModel.Results>> -> Response.Error(response.message)
                is Response.Success<List<CharactersApiModel.Results>> -> {
                    Response.Success(
                        response.data.toDomainModel(
                            favorites.map { it.id })
                    )
                }
            }
        }

    override suspend fun getCharactersByPage(page: String): Response<CharactersListModel> {
        return when (val response = api.getCharactersByPage(page)) {
            is Response.Error<CharactersApiModel> -> return Response.Error(response.message)
            is Response.Success<CharactersApiModel> -> Response.Success(response.data.toDomainModel(emptyList()))
        }
    }

    override suspend fun getCharactersByPageAndQuery(page: String?, query: String): Response<CharactersListModel> {
        val favorites = favoritesDao.getAll().firstOrNull() ?: emptyList()
        return when (val response = api.getCharactersByPageAndQuery(page, query)) {
            is Response.Error<CharactersApiModel> -> return Response.Error(response.message)
            is Response.Success<CharactersApiModel> -> Response.Success(response.data.toDomainModel(favorites.map { it.id }))
        }
    }

    override suspend fun getCharacterById(id: String): Response<CharacterDetailModel> {
        return when (val response = api.getCharacterById(id)) {
            is Response.Error<CharacterDetailApiModel> -> return Response.Error(response.message)
            is Response.Success<CharacterDetailApiModel> -> Response.Success(response.data.toDomainModel())
        }
    }
}
