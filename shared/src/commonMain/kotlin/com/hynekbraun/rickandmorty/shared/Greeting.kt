package com.hynekbraun.rickandmorty.shared

public class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}