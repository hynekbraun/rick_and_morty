package com.hynekbraun.rickandmorty.shared.di

import com.hynekbraun.rickandmorty.shared.features.characterdetail.CharacterDetailComponentsFactory
import com.hynekbraun.rickandmorty.shared.features.characterdetail.CharacterDetailComponentsFactoryImpl
import com.hynekbraun.rickandmorty.shared.features.characterslist.CharactersListComponentsFactory
import com.hynekbraun.rickandmorty.shared.features.characterslist.CharactersListComponentsFactoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

internal val sharedFeatureModule: Module = module {
    factory<CharactersListComponentsFactory> { CharactersListComponentsFactoryImpl() }
    factory<CharacterDetailComponentsFactory> { CharacterDetailComponentsFactoryImpl(get()) }
}
