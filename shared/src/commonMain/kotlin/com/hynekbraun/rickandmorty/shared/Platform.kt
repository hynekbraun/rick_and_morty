package com.hynekbraun.rickandmorty.shared

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform