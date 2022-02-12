package com.example.jetgames.core.domain.model.games

sealed class GameModel {
    data class GameItem(val game:Game):GameModel()
    data class SeparatorItem(val separator:String):GameModel()
}