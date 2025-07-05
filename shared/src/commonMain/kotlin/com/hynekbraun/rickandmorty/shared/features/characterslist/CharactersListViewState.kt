package com.hynekbraun.rickandmorty.shared.features.characterslist

import com.hynekbraun.rickandmorty.shared.components.components.CardCharacterComponentModel

public sealed class CharactersListViewState {
    public data object Loading : CharactersListViewState()
    public data class Data(val data: List<CardCharacterComponentModel>) : CharactersListViewState()
    public data object Error : CharactersListViewState()
}