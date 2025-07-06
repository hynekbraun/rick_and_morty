package com.hynekbraun.rickandmorty.shared.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_characters")
internal data class FavoriteCharacterEntity(
    @PrimaryKey
    val id: String,
)