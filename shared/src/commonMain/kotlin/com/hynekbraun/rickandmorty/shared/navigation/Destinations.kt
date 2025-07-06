package com.hynekbraun.rickandmorty.shared.navigation

import kotlinx.serialization.Serializable

public sealed class Destinations {

    public sealed class Maintab : Destinations() {
        @Serializable
        public data object Characters : Maintab()

        @Serializable
        public data object Favorites : Maintab()
    }

    @Serializable
    public data class Detail(public val characterId: String) : Destinations()

    @Serializable
    public data object Search : Destinations()

}
