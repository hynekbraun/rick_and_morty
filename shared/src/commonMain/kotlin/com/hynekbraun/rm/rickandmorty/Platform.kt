package com.hynekbraun.rm.rickandmorty

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform