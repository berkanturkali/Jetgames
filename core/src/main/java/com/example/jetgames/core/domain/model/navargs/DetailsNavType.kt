package com.example.jetgames.core.domain.model.navargs

import android.os.Bundle
import androidx.navigation.NavType
import com.squareup.moshi.Moshi
import com.squareup.moshi.addAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class DetailsNavType : NavType<DetailsArgs>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): DetailsArgs? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): DetailsArgs {
        val adapter = Moshi.Builder().add(KotlinJsonAdapterFactory()).build().adapter(DetailsArgs::class.java)
        return adapter.fromJson(value)!!
    }

    override fun put(bundle: Bundle, key: String, value: DetailsArgs) {
        bundle.putParcelable(key, value)
    }
}