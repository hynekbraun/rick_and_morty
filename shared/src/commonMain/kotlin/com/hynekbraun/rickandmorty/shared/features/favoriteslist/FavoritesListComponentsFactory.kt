package com.hynekbraun.rickandmorty.shared.features.favoriteslist

import com.hynekbraun.rickandmorty.shared.components.components.CardCharacterComponentModel
import com.hynekbraun.rickandmorty.shared.repository.models.CharacterModel

public interface FavoritesListComponentsFactory {

    public fun create(model: List<CharacterModel>): FavoritesListViewState.Data
}

internal class FavoritesListComponentsFactoryImpl : FavoritesListComponentsFactory {
    override fun create(model: List<CharacterModel>): FavoritesListViewState.Data {
        val characters = model.map { character ->
            CardCharacterComponentModel(
                photoUrl = character.photoUrl,
                name = character.name,
                id = character.id,
                status = character.status,
                showStar = character.isFavorite,
            )
        }
        return FavoritesListViewState.Data(characters = characters)
    }
}