package com.hynekbraun.rickandmorty.shared.features.characterdetail

import com.hynekbraun.rickandmorty.shared.components.components.DetailHeaderComponentModel
import com.hynekbraun.rickandmorty.shared.components.components.InformationComponentModel
import com.hynekbraun.rickandmorty.shared.repository.models.CharacterDetailModel
import com.hynekbraun.rickandmorty.shared.resources.StringRes

public interface CharacterDetailComponentsFactory {

    public fun create(model: CharacterDetailModel): CharacterDetailViewState.Data
}

internal class CharacterDetailComponentsFactoryImpl(
    private val stringRes: StringRes,
) : CharacterDetailComponentsFactory {
    override fun create(model: CharacterDetailModel): CharacterDetailViewState.Data {
        return CharacterDetailViewState.Data(
            header = DetailHeaderComponentModel(
                name = model.name,
                title = stringRes.name,
                imageUrl = model.photoUrl,
            ),
            info = listOf(
                InformationComponentModel(
                    title = stringRes.status,
                    value = model.status.placeholderIfEmpty(),
                ),
                InformationComponentModel(
                    title = stringRes.species,
                    value = model.species.placeholderIfEmpty(),
                ),
                InformationComponentModel(
                    title = stringRes.type,
                    value = model.type.placeholderIfEmpty(),
                ),
                InformationComponentModel(
                    title = stringRes.gender,
                    value = model.gender.placeholderIfEmpty(),
                ),
                InformationComponentModel(
                    title = stringRes.origin,
                    value = model.origin.placeholderIfEmpty(),
                ),
                InformationComponentModel(
                    title = stringRes.location,
                    value = model.location.placeholderIfEmpty(),
                ),
            )
        )
    }
}

private fun String.placeholderIfEmpty() =
    this.takeIf { it.isNotEmpty() } ?: "-"