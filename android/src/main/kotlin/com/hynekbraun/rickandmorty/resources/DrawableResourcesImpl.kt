package com.hynekbraun.rickandmorty.resources

import android.content.Context
import com.hynekbraun.rickandmorty.components.R
import com.hynekbraun.rickandmorty.shared.components.utils.DrawableRes
import com.hynekbraun.rickandmorty.shared.resources.DrawableResources

internal class DrawableResourcesImpl(private val context: Context): DrawableResources {
    override val actionBarFavoritesFilled: DrawableRes
        get() = R.drawable.favorites_active
    override val actionBarFavoritesEmpty: DrawableRes
        get() = R.drawable.favorites_empty
    override val actionBarBackChevron: DrawableRes
        get() = R.drawable.chevron_back

}