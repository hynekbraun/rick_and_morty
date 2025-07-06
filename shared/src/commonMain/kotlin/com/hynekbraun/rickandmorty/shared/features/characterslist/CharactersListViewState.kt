package com.hynekbraun.rickandmorty.shared.features.characterslist

import com.hynekbraun.rickandmorty.shared.components.components.CardCharacterComponentModel
import com.hynekbraun.rickandmorty.shared.components.components.NextPageComponentModel

public sealed class CharactersListViewState {
    public data object Loading : CharactersListViewState()
    public data class Data(
        val characters: List<CardCharacterComponentModel>,
        val nextPage: NextPageComponentModel?,
    ) : CharactersListViewState()

    public data object Error : CharactersListViewState()
}