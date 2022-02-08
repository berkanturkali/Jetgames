package com.example.jetgames.remote.factory

import com.example.jetgames.remote.interceptor.NoInternetInterceptor
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

public class RemoteFactory @Inject constructor(
    private val moshi:Moshi
){

    public fun createRetrofit(url: String, isDebug: Boolean): Retrofit {
        val client: OkHttpClient = makeOkHttpClient(
            makeLoggingInterceptor((isDebug))
        )
        return Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        if(isDebug) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        }
        else {logging.level = HttpLoggingInterceptor.Level.NONE}
        return logging
    }

    private fun makeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(NoInternetInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()
    }
}