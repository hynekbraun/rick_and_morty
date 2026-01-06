package com.hynekbraun.rickandmorty.shared.navigation

import kotlinx.serialization.Serializable

@Serializable
public sealed class Destinations : KmpNavKey {

    @Serializable
    public data object CharactersTab : Destinations()

    @Serializable
    public data object FavoritesTab : Destinations()

    @Serializable
    public data class Detail(public val characterId: String) : Destinations()

    @Serializable
    public data object Search : Destinations()

    @Serializable
    public data class PictureDialog(public val photoUrl: String) : Destinations()
}
