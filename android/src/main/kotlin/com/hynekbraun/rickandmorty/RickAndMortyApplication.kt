package com.hynekbraun.rickandmorty

import android.app.Application
import com.hynekbraun.rickandmorty.di.androidResourcesModule
import com.hynekbraun.rickandmorty.shared.di.initKoin
import org.koin.android.ext.koin.androidContext

internal class RickAndMortyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin(platformModules = platformModules) {
            androidContext(this@RickAndMortyApplication)
        }
    }
}

private val platformModules = listOf(
    androidResourcesModule
)