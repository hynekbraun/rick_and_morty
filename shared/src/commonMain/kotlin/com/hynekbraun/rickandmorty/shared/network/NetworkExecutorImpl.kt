package com.hynekbraun.rickandmorty.shared.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.reflect.TypeInfo
import kotlin.reflect.KClass
import kotlinx.serialization.json.Json

internal class NetworkExecutorImpl : NetworkExecutor {

    private val httpClient: HttpClient =
        HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }
        }

    override suspend fun <T : Any> get(url: String, clazz: KClass<T>): Response<T> {
        val response = try {
            httpClient.get(url)
        } catch (e: Exception) {
            return Response.Error("Something went wrong")
        }

        return when (response.status.value) {
            in 200..299 -> {
                Response.Success(response.body(TypeInfo(clazz)))
            }

            else -> {
                Response.Error("Something went wrong")
            }
        }
    }
}
