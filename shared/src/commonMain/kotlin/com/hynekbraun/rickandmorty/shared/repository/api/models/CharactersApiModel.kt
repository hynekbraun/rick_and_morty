package com.hynekbraun.rickandmorty.shared.repository.api.models

import com.hynekbraun.rickandmorty.shared.repository.models.CharacterModel
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
        val nextPage: String,
    )

    @Serializable
    internal data class Results(
        @SerialName("id")
        val id: String,
        @SerialName("name")
        val name: String,
        @SerialName("status")
        val status: String,
        @SerialName("image")
        val photoUrl: String,
    )
}

internal fun CharactersApiModel.Results.toDomainModel(): CharacterModel = CharacterModel(
    id = this.id,
    name = this.name,
    status = this.status,
    photoUrl = this.photoUrl,
)