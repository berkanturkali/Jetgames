package com.example.jetgames.core.domain.model.preferences

enum class Order(val value:String) {
    RATING("rating"),
    METACRITIC("metacritic")
}

enum class ASCDESC(val value:Char){
    ASCENDING('+'),
    DESCENDING('-')
}