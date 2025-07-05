package com.hynekbraun.rickandmorty.shared.features.characterslist

import com.hynekbraun.rickandmorty.shared.components.components.CardCharacterComponentModel
import com.hynekbraun.rickandmorty.shared.repository.models.CharacterModel

public interface CharactersListComponentsFactory {

    public fun create(model: List<CharacterModel>): List<CardCharacterComponentModel>
}

internal class CharactersListComponentsFactoryImpl() : CharactersListComponentsFactory {
    override fun create(model: List<CharacterModel>): List<CardCharacterComponentModel> {
        return model.map { character ->
            CardCharacterComponentModel(
                photoUrl = character.photoUrl,
                name = character.name,
                id = character.id,
                status = character.status,
                showStar = false, // TODO favorites
            )
        }
    }

}