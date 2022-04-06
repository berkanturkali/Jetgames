package com.example.jetgames.core.remote.util

import com.example.jetgames.core.BuildConfig

internal const val API_KEY = BuildConfig.API_KEY
internal const val ORDERING = "-metacritic"
internal const val PAGE = 1
internal const val SIZE = 20
internal const val ID = 3498
internal const val SEARCH_QUERY = "god-of-war"
internal const val SEARCH_EXACT = true
internal const val NO_MATCH_SEARCH_QUERY = "no-match-search-query"

// response
internal const val GAMES_RESPONSE_PATH = "response/games.json"
internal const val GAMES_DETAIL_RESPONSE_PATH = "response/detail_response.json"
internal const val SEARCH_RESPONSE_PATH = "response/search_response.json"
internal const val NO_MATCH_RESPONSE_PATH = "response/empty_search_response.json"
internal const val PLATFORMS_RESPONSE_PATH = "response/platforms_response.json"

// path
internal const val GAMES_URL_PATH = "$REQUEST_PATH?page=$PAGE&page_size=$SIZE&key=$API_KEY&ordering=$ORDERING&search_exact=$SEARCH_EXACT"
internal const val GAMES_URL_W_SEARCH_QUERY_PATH = "$REQUEST_PATH?page=$PAGE&page_size=$SIZE&key=$API_KEY&ordering=$ORDERING&search=$SEARCH_QUERY&search_exact=$SEARCH_EXACT"
internal const val GAMES_URL_W_NO_MATCH_QUERY_PATH = "$REQUEST_PATH?page=$PAGE&page_size=$SIZE&key=$API_KEY&ordering=$ORDERING&search=$NO_MATCH_SEARCH_QUERY&search_exact=$SEARCH_EXACT"
internal const val DETAILS_URL_PATH = "$REQUEST_PATH/$ID?key=$API_KEY"
internal const val PLATFORMS_URL_PATH = "$PLATFORMS_PATH?key=$API_KEY"
