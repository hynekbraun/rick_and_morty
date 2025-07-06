package com.hynekbraun.rickandmorty.shared.di

import com.hynekbraun.rickandmorty.shared.database.FavoriteCharactersDatabase
import com.hynekbraun.rickandmorty.shared.database.getFavoriteCharactersDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual val sharedPlatformDatabaseModule: Module = module {
    single<FavoriteCharactersDatabase> { getFavoriteCharactersDatabase() }
}
