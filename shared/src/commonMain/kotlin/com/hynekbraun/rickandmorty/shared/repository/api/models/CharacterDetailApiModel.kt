package com.hynekbraun.rickandmorty.shared.repository.api.models

import com.hynekbraun.rickandmorty.shared.repository.models.CharacterDetailModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class CharacterDetailApiModel(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("image")
    val image: String,
    @SerialName("status")
    val status: String,
    @SerialName("species")
    val species: String,
    @SerialName("type")
    val type: String,
    @SerialName("gender")
    val gender: String,
    @SerialName("origin")
    val origin: Origin,
    @SerialName("location")
    val location: Location,

    ) {
    @Serializable
    internal data class Origin(
        @SerialName("name")
        val name: String,
    )

    @Serializable
    internal data class Location(
        @SerialName("name")
        val name: String,
    )
}

internal fun CharacterDetailApiModel.toDomainModel(): CharacterDetailModel = CharacterDetailModel(
    photoUrl = this.image,
    id = this.id,
    name = this.name,
    status = this.status,
    species = this.species,
    type = this.type,
    gender = this.gender,
    origin = this.origin.name,
    location = this.location.name,
)
