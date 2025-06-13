package com.example.jetgames.remote.factory

import com.example.jetgames.remote.interceptor.ApiKeyInterceptor
import com.example.jetgames.remote.interceptor.NoInternetInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

public class RemoteFactory  @Inject constructor() {

    public fun createRetrofit(url: String, isDebug: Boolean, key: String): Retrofit {
        val client: OkHttpClient = makeOkHttpClient(
            makeLoggingInterceptor((isDebug)),
            key = key
        )
        return Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().add(
                KotlinJsonAdapterFactory()
            ).build()))
            .build()
    }

    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }

    private fun makeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, key: String): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(NoInternetInterceptor)
            .addInterceptor(ApiKeyInterceptor(key))
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()
    }
}
