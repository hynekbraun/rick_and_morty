package com.hynekbraun.rickandmorty.shared.di

import com.hynekbraun.rickandmorty.shared.network.NetworkExecutor
import com.hynekbraun.rickandmorty.shared.network.NetworkExecutorImpl
import org.koin.core.module.Module
import org.koin.dsl.module

internal val sharedNetworkModule: Module = module {
    single<NetworkExecutor> { NetworkExecutorImpl() }
}
