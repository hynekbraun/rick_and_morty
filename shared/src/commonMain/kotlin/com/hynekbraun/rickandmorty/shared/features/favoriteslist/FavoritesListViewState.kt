package com.hynekbraun.rickandmorty.shared.features.favoriteslist

import com.hynekbraun.rickandmorty.shared.components.components.CardCharacterComponentModel

public sealed class FavoritesListViewState {
    public data object Loading : FavoritesListViewState()
    public data class Data(val characters: List<CardCharacterComponentModel>) : FavoritesListViewState()
    public data object Error : FavoritesListViewState()
}