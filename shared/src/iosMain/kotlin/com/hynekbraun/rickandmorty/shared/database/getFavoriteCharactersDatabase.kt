package com.hynekbraun.rickandmorty.shared.database

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import platform.Foundation.NSHomeDirectory

internal fun getFavoriteCharactersDatabase(): FavoriteCharactersDatabase {
    val dbFile = NSHomeDirectory() + "/favorite_characters.db"
    return Room.databaseBuilder<FavoriteCharactersDatabase>(name = dbFile)
        .setDriver(BundledSQLiteDriver())
        .build()
}
