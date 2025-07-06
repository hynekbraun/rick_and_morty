package com.hynekbraun.rickandmorty.shared.di

import com.hynekbraun.rickandmorty.shared.features.characterdetail.CharacterDetailViewModel
import com.hynekbraun.rickandmorty.shared.features.characterslist.CharactersListViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual val viewModelModule : Module = module {
    factory { CharactersListViewModel(get(), get()) }
    factory { (characterId: String) ->
        CharacterDetailViewModel(
            charactersRepository = get(),
            favoritesRepository = get(),
            characterId = characterId,
            componentFactory = get(),
            drawableResources = get(),
        )
    }
}