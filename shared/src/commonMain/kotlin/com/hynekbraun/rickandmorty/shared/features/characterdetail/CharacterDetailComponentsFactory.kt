package com.hynekbraun.rickandmorty.shared.features.characterdetail

import com.hynekbraun.rickandmorty.shared.components.components.DetailHeaderComponentModel
import com.hynekbraun.rickandmorty.shared.components.components.InformationComponentModel
import com.hynekbraun.rickandmorty.shared.repository.models.CharacterDetailModel

public interface CharacterDetailComponentsFactory {

    public fun create(model: CharacterDetailModel): CharacterDetailViewState.Data
}
// TODO add trans from platform
internal class CharacterDetailComponentsFactoryImpl() : CharacterDetailComponentsFactory {
    override fun create(model: CharacterDetailModel): CharacterDetailViewState.Data {
        return CharacterDetailViewState.Data(
            header = DetailHeaderComponentModel(
                name = "Name",
                title = model.status,
                imageUrl = model.photoUrl,
            ),
            info = listOf(
                InformationComponentModel(
                    title = "Status",
                    value = model.status,
                ),
                InformationComponentModel(
                    title = "Species",
                    value = model.species,
                ),
                InformationComponentModel(
                    title = "Type",
                    value = model.type,
                ),
                InformationComponentModel(
                    title = "Gender",
                    value = model.gender,
                ),
                InformationComponentModel(
                    title = "Origin",
                    value = model.origin,
                ),
                InformationComponentModel(
                    title = "Location",
                    value = model.location,
                ),
            )
        )
    }

}