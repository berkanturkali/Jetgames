package com.example.jetgames.core.cache.converters

import androidx.room.TypeConverter
import com.example.jetgames.core.cache.model.PlatformEntity
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.lang.reflect.ParameterizedType

internal class RoomConverters(
) {

    private val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private val platformAdapter: JsonAdapter<List<PlatformEntity>> by lazy {
        val type: ParameterizedType =
            Types.newParameterizedType(List::class.java, PlatformEntity::class.java)
        moshi.adapter(type)
    }

    private val listAdapter: JsonAdapter<List<String>> by lazy {
        val type: ParameterizedType =
            Types.newParameterizedType(List::class.java, String::class.java)
        moshi.adapter(type)
    }

    @TypeConverter
    fun fromPlatforms(platforms: List<PlatformEntity>?): String {
        return platformAdapter.toJson(platforms)
    }

    @TypeConverter
    fun fromPlatformsString(platforms: String): List<PlatformEntity>? {
        return platformAdapter.fromJson(platforms)
    }

    @TypeConverter
    fun fromStringList(list: List<String>?): String {
        return listAdapter.toJson(list)
    }

    @TypeConverter
    fun toStringList(string: String): List<String>? {
        return listAdapter.fromJson(string)
    }
}