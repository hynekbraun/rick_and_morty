package com.hynekbraun.rickandmorty.resources

import android.content.Context
import com.hynekbraun.rickandmorty.R
import com.hynekbraun.rickandmorty.shared.resources.StringRes

internal class StringResImpl(
    private val context: Context,
): StringRes {
    override val name: String
        get() = context.getString(R.string.detail_name)
    override val status: String
        get() = context.getString(R.string.detail_status)
    override val species: String
        get() = context.getString(R.string.detail_species)
    override val type: String
        get() = context.getString(R.string.detail_type)
    override val gender: String
        get() = context.getString(R.string.detail_gender)
    override val origin: String
        get() = context.getString(R.string.detail_origin)
    override val location: String
        get() = context.getString(R.string.detail_location)
}