package com.example.jetgames.core.domain.model.navargs.base

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

abstract class BaseNavType<T : Parcelable>(
    override val isNullableAllowed: Boolean,
) : NavType<T>(isNullableAllowed = isNullableAllowed) {

    protected val moshi: Moshi by lazy {
        Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    override fun get(bundle: Bundle, key: String): T? {
        return bundle.getParcelable(key)
    }

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putParcelable(key, value)
    }

    inline fun <reified T> Moshi.parseValue(value: String): T {
        return adapter<T>(T::class.java).fromJson(
            value
        )!!
    }
}
