package com.example.jetgames.core.remote.util

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.net.HttpURLConnection

class RequestDispatcher : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            GAMES_URL_PATH -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson(GAMES_RESPONSE_PATH))
            }
            DETAILS_URL_PATH -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson(GAMES_DETAIL_RESPONSE_PATH))
            }
            GAMES_URL_W_SEARCH_QUERY_PATH -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson(SEARCH_RESPONSE_PATH))
            }
            GAMES_URL_W_NO_MATCH_QUERY_PATH -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson(NO_MATCH_RESPONSE_PATH))
            }
            PLATFORMS_URL_PATH -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson(PLATFORMS_RESPONSE_PATH))
            }
            else -> throw IllegalArgumentException("Unknown Request Path ${request.path}")
        }
    }
}
