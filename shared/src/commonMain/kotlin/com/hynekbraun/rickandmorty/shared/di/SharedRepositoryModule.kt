package com.hynekbraun.rickandmorty.shared.di

import com.hynekbraun.rickandmorty.shared.repository.CharactersRepository
import com.hynekbraun.rickandmorty.shared.repository.CharactersRepositoryImpl
import com.hynekbraun.rickandmorty.shared.repository.FavoriteCharactersRepository
import com.hynekbraun.rickandmorty.shared.repository.FavoriteCharactersRepositoryImpl
import com.hynekbraun.rickandmorty.shared.repository.api.CharactersApi
import com.hynekbraun.rickandmorty.shared.repository.api.CharactersApiImpl
import org.koin.core.module.Module
import org.koin.dsl.module

internal val sharedRepositoryModule: Module = module {
    single<CharactersApi> { CharactersApiImpl(get()) }
    single<CharactersRepository> { CharactersRepositoryImpl(get(), get()) }

    single<FavoriteCharactersRepository> { FavoriteCharactersRepositoryImpl(get()) }
}
