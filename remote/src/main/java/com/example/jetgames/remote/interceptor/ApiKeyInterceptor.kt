package com.example.jetgames.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response

internal data class ApiKeyInterceptor(
    private val key: String,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val newUrl = request.url.newBuilder()
            .addQueryParameter("key", key)
            .build()

        val newRequest = request.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}
