package com.hynekbraun.rickandmorty.shared.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

public fun initKoin(
    platformModules: List<Module> = emptyList(),
    appDeclaration: KoinApplication.() -> Unit = {},

    ) {
    startKoin {
        appDeclaration
        modules(platformModules + sharedModules)
    }
}

internal val sharedModules: Module =
    module {
        includes(
            sharedNetworkModule,
            sharedRepositoryModule,
            viewModelModule,
            sharedFeatureModule,
        )
    }
