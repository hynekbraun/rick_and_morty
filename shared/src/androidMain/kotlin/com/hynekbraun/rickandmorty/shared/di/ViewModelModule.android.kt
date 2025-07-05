package com.hynekbraun.rickandmorty.shared.di

import com.hynekbraun.rickandmorty.shared.features.characterslist.CharactersListViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

internal actual val viewModelModule: Module = module {
    viewModelOf(::CharactersListViewModel)
}