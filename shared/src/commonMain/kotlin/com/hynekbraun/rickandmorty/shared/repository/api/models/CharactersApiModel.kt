package com.hynekbraun.rickandmorty.shared.repository.api.models

import com.hynekbraun.rickandmorty.shared.repository.models.CharacterModel
import com.hynekbraun.rickandmorty.shared.repository.models.CharactersListModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class CharactersApiModel(
    @SerialName("info")
    val info: Info,
    @SerialName("results")
    val characters: List<Results>,
) {
    @Serializable
    internal data class Info(
        @SerialName("next")
        val nextPage: String?,
    )

    @Serializable
    internal data class Results(
        @SerialName("id")
        val id: Int,
        @SerialName("name")
        val name: String,
        @SerialName("status")
        val status: String,
        @SerialName("image")
        val photoUrl: String,
    )
}

internal fun CharactersApiModel.toDomainModel(favoritesId: List<String>): CharactersListModel = CharactersListModel(
    nextPage = this.info.nextPage,
    characters = this.characters.map { character ->
        CharacterModel(
            id = character.id.toString(),
            name = character.name,
            status = character.status,
            photoUrl = character.photoUrl,
            isFavorite = favoritesId.contains(character.id.toString()),
        )
    },
)

internal  fun List<CharactersApiModel.Results>.toDomainModel(favoritesId: List<String>): List<CharacterModel> = this.map {
    CharacterModel(
        id = it.id.toString(),
        name = it.name,
        status = it.status,
        photoUrl = it.photoUrl,
        isFavorite = favoritesId.contains(it.id.toString()),
    )
}