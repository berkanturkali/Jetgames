package com.example.jetgames.core.remote.util

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.net.HttpURLConnection

class RequestDispatcher : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            "$REQUEST_PATH?page=$PAGE&page_size=$SIZE&key=$API_KEY&ordering=$ORDERING" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson(GAMES_RESPONSE_PATH))
            }
            "$REQUEST_PATH/$ID?key=$API_KEY" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson(GAMES_DETAIL_RESPONSE_PATH))
            }
            "$REQUEST_PATH?page=$PAGE&page_size=$SIZE&key=$API_KEY&ordering=$ORDERING&search=$SEARCH_QUERY" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson(SEARCH_RESPONSE_PATH))
            }
            "$REQUEST_PATH?page=$PAGE&page_size=$SIZE&key=$API_KEY&ordering=$ORDERING&search=$NO_MATCH_SEARCH_QUERY" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson(NO_MATCH_RESPONSE_PATH))
            }
            else -> throw IllegalArgumentException("Unknown Request Path ${request.path}")
        }
    }
}