package com.hynekbraun.rickandmorty.shared.di

import com.hynekbraun.rickandmorty.shared.features.characterdetail.CharacterDetailViewModel
import com.hynekbraun.rickandmorty.shared.features.characterslist.CharactersListViewModel
import org.koin.core.module.Module
import org.koin.core.module.factory
import org.koin.dsl.module

internal actual val viewModelModule : Module = module {
    factory { CharactersListViewModel(get(), get()) }
    factory { (characterId: String) ->
        CharacterDetailViewModel(
            repository = get(),
            characterId = characterId,
            componentFactory = get(),
            drawableResources = get(),
        )
    }
}