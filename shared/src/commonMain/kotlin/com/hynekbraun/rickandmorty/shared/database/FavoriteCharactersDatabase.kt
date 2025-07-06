package com.hynekbraun.rickandmorty.shared.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor

@Database(
    entities = [FavoriteCharacterEntity::class],
    version = 1,
)
@ConstructedBy(FavoriteCharactersDatabaseConstructor::class)
internal abstract class FavoriteCharactersDatabase : RoomDatabase() {
    internal  abstract fun favoriteCharactersDao(): FavoriteCharactersDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT", "EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
internal expect object FavoriteCharactersDatabaseConstructor : RoomDatabaseConstructor<FavoriteCharactersDatabase> {
    override fun initialize(): FavoriteCharactersDatabase
}