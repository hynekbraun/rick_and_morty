package com.hynekbraun.rickandmorty.shared.database

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver

internal fun getFavoriteCharactersDatabase(context: Context): FavoriteCharactersDatabase {
    val dbFile = context.getDatabasePath("favorite_characters.db")
    return Room.databaseBuilder<FavoriteCharactersDatabase>(
        context = context.applicationContext,
        name = dbFile.absolutePath,
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}