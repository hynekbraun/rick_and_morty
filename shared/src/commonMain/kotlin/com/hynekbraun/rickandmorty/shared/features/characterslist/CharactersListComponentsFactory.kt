package com.hynekbraun.rickandmorty.shared.features.characterslist

import com.hynekbraun.rickandmorty.shared.components.components.CardCharacterComponentModel
import com.hynekbraun.rickandmorty.shared.components.components.NextPageComponentModel
import com.hynekbraun.rickandmorty.shared.repository.models.CharacterModel

public interface CharactersListComponentsFactory {

    public fun create(model: List<CharacterModel>, hasNextPage: Boolean): CharactersListViewState.Data
}

internal class CharactersListComponentsFactoryImpl() : CharactersListComponentsFactory {
    override fun create(model: List<CharacterModel>, hasNextPage: Boolean): CharactersListViewState.Data {
        val characters = model.map { character ->
            CardCharacterComponentModel(
                photoUrl = character.photoUrl,
                name = character.name,
                id = character.id,
                status = character.status,
                showStar = character.isFavorite,
            )
        }

        return CharactersListViewState.Data(
            characters = characters,
            nextPage = if (hasNextPage) NextPageComponentModel else null,
        )
    }

}