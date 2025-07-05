package com.hynekbraun.rickandmorty.shared.network

public sealed interface Response<T : Any> {
    public data class Success<T : Any>(val data: T) : Response<T>
    public data class Error<T : Any>(val message: String?) : Response<T>

    public fun isSuccess(): Boolean {
        return this is Success
    }

    public fun data(): T? {
        return if (this is Success) this.data else null
    }
}
