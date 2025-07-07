package com.hynekbraun.rickandmorty.shared.features.search

import com.hynekbraun.rickandmorty.shared.components.components.CardCharacterSearchComponentModel
import com.hynekbraun.rickandmorty.shared.components.components.NextPageComponentModel
import com.hynekbraun.rickandmorty.shared.repository.models.CharacterModel

public interface SearchComponentsFactory {

    public fun create(model: List<CharacterModel>, hasNextPage: Boolean): SearchViewState.Data

}

internal class SearchComponentsFactoryImpl : SearchComponentsFactory {
    override fun create(model: List<CharacterModel>, hasNextPage: Boolean): SearchViewState.Data {
        val characters = model.map { character ->
            CardCharacterSearchComponentModel(
                photoUrl = character.photoUrl,
                name = character.name,
                status = character.status,
                id = character.id,
            )
        }
        return if (hasNextPage) {
            SearchViewState.Data(
                characters = characters,
                nextPage = null,
            )
        } else {
            SearchViewState.Data(
                characters = characters,
                nextPage = NextPageComponentModel,
            )
        }
    }
}
