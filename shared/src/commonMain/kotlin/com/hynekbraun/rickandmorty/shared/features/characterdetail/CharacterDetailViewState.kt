package com.hynekbraun.rickandmorty.shared.features.characterdetail

import com.hynekbraun.rickandmorty.shared.components.components.DetailHeaderComponentModel
import com.hynekbraun.rickandmorty.shared.components.components.InformationComponentModel

public sealed class CharacterDetailViewState {
    public data object Loading : CharacterDetailViewState()
    public data class Data(
        val header: DetailHeaderComponentModel,
        val info: List<InformationComponentModel>,
    ) : CharacterDetailViewState()
    public data object Error : CharacterDetailViewState()
}