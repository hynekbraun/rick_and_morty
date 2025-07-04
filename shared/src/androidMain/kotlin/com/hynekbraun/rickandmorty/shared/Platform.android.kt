package com.hynekbraun.rickandmorty.shared

import android.os.Build

public class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

public actual fun getPlatform(): Platform = AndroidPlatform()