package com.hynekbraun.rickandmorty.shared.network

import kotlin.reflect.KClass

internal interface NetworkExecutor {
    suspend fun <T : Any> get(url: String, clazz: KClass<T>): Response<T>
}

internal suspend inline fun <reified T : Any> NetworkExecutor.get(url: String): Response<T> = get(url, T::class)
