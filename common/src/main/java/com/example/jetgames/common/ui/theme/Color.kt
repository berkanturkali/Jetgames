package com.example.jetgames.common.ui.theme

import androidx.compose.material.darkColors
import androidx.compose.ui.graphics.Color

val Blue = Color(0XFF1DA1F2)
val Black = Color(0XFF14171A)
val XBlack = Color(0XFF34373A)
val XXBlack = Color(0XFF525559)
val DarkGray = Color(0XFF657786)
val LightGray = Color(0XFFAAB8C2)
val XLightGray = Color(0XFFE1E8ED)
val XXLightGray = Color(0XFFF5F8FA)
val Error = Color(0xFFB00020)

val JetgamesColors = darkColors(
    primary = Black,
    onPrimary = XXLightGray,
    primaryVariant = XBlack,
    secondary = Blue,
    onSecondary = XXLightGray,
    error = Error,
    onError = XXLightGray,
)
