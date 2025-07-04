package com.hynekbraun.rickandmorty.shared

public class Greeting {
    private val platform = getPlatform()

    public fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}