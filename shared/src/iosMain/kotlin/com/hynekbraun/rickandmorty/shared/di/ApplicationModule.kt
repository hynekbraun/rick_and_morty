package com.hynekbraun.rickandmorty.shared.di

import com.hynekbraun.rickandmorty.shared.resources.DrawableResources
import com.hynekbraun.rickandmorty.shared.resources.StringRes
import org.koin.core.module.Module
import org.koin.dsl.module

public object ApplicationModule {

    public fun createKoinModule(
        strings: () -> StringRes,
        drawables: () -> DrawableResources,
    ): Module =
        module {
            factory { strings() }
            factory { drawables() }
        }
}
