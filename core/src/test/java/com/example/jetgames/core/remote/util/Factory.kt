package com.example.jetgames.core.remote.util

import com.example.jetgames.core.remote.model.games.GamesResponse
import com.example.jetgames.core.remote.model.platforms.PlatformsResponse
import com.example.jetgames.core.remote.service.ApiService
import com.google.common.io.Resources
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.lang.reflect.ParameterizedType
import java.net.URL

internal const val REQUEST_PATH: String = "/games"
internal const val PLATFORMS_PATH: String = "/platforms"
private val okHttpClient: OkHttpClient
    get() = OkHttpClient.Builder().build()

internal val moshi: Moshi
    get() = Moshi.Builder()
        .add(KotlinJsonAdapterFactory()).build()

internal val gamesResponseAdapter: JsonAdapter<GamesResponse>
    get() {
        val type: ParameterizedType = Types.newParameterizedType(
            GamesResponse::class.java,
            String::class.java
        )
        return moshi.adapter(type)
    }

internal val platformsResponseAdapter: JsonAdapter<PlatformsResponse>
    get() {
        val type: ParameterizedType = Types.newParameterizedType(
            PlatformsResponse::class.java,
            String::class.java
        )
        return moshi.adapter(type)
    }

internal fun getJson(path: String): String {
    val uri: URL = Resources.getResource(path)
    val file = File(uri.path)
    return String(file.readBytes())
}

internal fun makeApiService(mockWebServer: MockWebServer): ApiService = Retrofit.Builder()
    .baseUrl(mockWebServer.url("/"))
    .client(okHttpClient)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()
    .create(ApiService::class.java)
