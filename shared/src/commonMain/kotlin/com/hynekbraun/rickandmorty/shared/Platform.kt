package com.hynekbraun.rickandmorty.shared

public interface Platform {
    public val name: String
}

public expect fun getPlatform(): Platform