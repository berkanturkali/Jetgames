package com.example.jetgames.core.domain.model.games

data class Rating(
    val count: Int?,
    val id: Int?,
    val percent: Double?,
    val title: String?,
    var icon: String? = null,
) {

    fun setIcon() {
        title?.let {
            icon = when (it) {
                "exceptional" -> "\uD83C\uDFAF"
                "recommended" -> "\uD83D\uDC4D"
                "skip" -> "\u26D4"
                "meh" -> "\uD83D\uDE11"
                else -> null
            }
        }
    }
}

/*
recomended = "\uD83D\uDC4D"
exceptional = "\uD83C\uDFAF"
skip = "\u26D4"
meh = "\uD83D\uDE11"
 */