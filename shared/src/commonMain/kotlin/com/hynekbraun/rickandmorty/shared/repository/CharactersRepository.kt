package com.hynekbraun.rickandmorty.shared.repository

import com.hynekbraun.rickandmorty.shared.network.Response
import com.hynekbraun.rickandmorty.shared.repository.models.CharacterDetailModel
import com.hynekbraun.rickandmorty.shared.repository.models.CharactersListModel

public interface CharactersRepository {

    public suspend fun getCharacters(): Response<CharactersListModel>

    public suspend fun getCharactersByPage(page: String): Response<CharactersListModel>

    public suspend fun getCharacterById(id: String): Response<CharacterDetailModel>
}
