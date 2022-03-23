package com.example.jetgames.core.utils


import androidx.compose.ui.graphics.Color


fun Double.calculateRgbFromRating(): Color {
    val percent = if (this == 1.0) (this - 1) * 0.2 else this * 0.2
    val red = 255
    val green = percent * 255
    val blue = 0
    return Color(red, green.toInt(), blue)
}

/*
   https://stackoverflow.com/questions/6394304/algorithm-how-do-i-fade-from-red-to-green-via-yellow-using-rgb-values
*/
fun Int.calculateRgbFromMetacritic(): Color {
    val percent = this * 0.01
    val red = (255 - (percent * 255)) * 2
    val green = (percent * 255 * 2)
    val blue = 0
    return Color(minOf(255, red.toInt()), minOf(255, green.toInt()), blue)
}