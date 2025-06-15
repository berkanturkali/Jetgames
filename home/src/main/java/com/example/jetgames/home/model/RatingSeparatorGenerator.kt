package com.example.jetgames.home.model

import com.example.jetgames.core.domain.model.games.GameModel

object RatingSeparatorGenerator: SeparatorGenerator {
    override fun generateInitial(after: GameModel.GameItem): GameModel.SeparatorItem? {
        return after.game.rating?.toInt()?.let { rating ->
            val plus = if (rating == 5) "" else "+"
            GameModel.SeparatorItem("${rating}$plus Stars")
        }
    }

    override fun generateBetween(before: GameModel.GameItem, after: GameModel.GameItem): GameModel.SeparatorItem? {
        val beforeRating = before.game.rating?.toInt()
        val afterRating = after.game.rating?.toInt()
        return if (beforeRating != null && afterRating != null && afterRating < beforeRating) {
            GameModel.SeparatorItem("$afterRating + Stars")
        } else null
    }
}