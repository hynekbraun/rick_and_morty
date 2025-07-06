package com.hynekbraun.rickandmorty.shared.di

import com.hynekbraun.rickandmorty.shared.database.FavoriteCharactersDao
import com.hynekbraun.rickandmorty.shared.database.FavoriteCharactersDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

internal val sharedDatabaseModule: Module = module {
    includes(sharedPlatformDatabaseModule)
    single<FavoriteCharactersDao> {
        get<FavoriteCharactersDatabase>().favoriteCharactersDao()
    }

}
internal expect val sharedPlatformDatabaseModule: Module