package com.hynekbraun.rickandmorty.shared.features.search

import com.hynekbraun.rickandmorty.shared.components.components.CardCharacterSearchComponentModel
import com.hynekbraun.rickandmorty.shared.components.components.NextPageComponentModel

public sealed class SearchViewState {
    public data object Initial : SearchViewState()
    public data class Data(
        val characters: List<CardCharacterSearchComponentModel>,
        val nextPage: NextPageComponentModel?,
    ) : SearchViewState()

    public data object Error : SearchViewState()
}