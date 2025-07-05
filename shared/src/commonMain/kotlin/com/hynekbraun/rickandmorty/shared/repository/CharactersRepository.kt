package com.hynekbraun.rickandmorty.shared.repository

import com.hynekbraun.rickandmorty.shared.network.Response
import com.hynekbraun.rickandmorty.shared.repository.models.CharacterModel

public interface CharactersRepository {

    public suspend fun getCharacters(): Response<List<CharacterModel>>
}
