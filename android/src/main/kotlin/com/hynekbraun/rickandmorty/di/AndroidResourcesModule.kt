package com.hynekbraun.rickandmorty.di

import com.hynekbraun.rickandmorty.resources.StringResImpl
import com.hynekbraun.rickandmorty.shared.resources.StringRes
import org.koin.core.module.Module
import org.koin.dsl.module

internal val androidResourcesModule: Module = module {
    single<StringRes> { StringResImpl(get()) }
}