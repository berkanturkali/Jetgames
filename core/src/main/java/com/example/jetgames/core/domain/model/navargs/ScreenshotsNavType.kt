package com.example.jetgames.core.domain.model.navargs

import android.os.Bundle
import androidx.navigation.NavType
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class ScreenshotsNavType: NavType<ScreenshotsArgs>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): ScreenshotsArgs? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): ScreenshotsArgs {
        val adapter = Moshi.Builder().add(KotlinJsonAdapterFactory()).build().adapter(ScreenshotsArgs::class.java)
        return adapter.fromJson(value)!!
    }

    override fun put(bundle: Bundle, key: String, value: ScreenshotsArgs) {
        bundle.putParcelable(key, value)
    }
}