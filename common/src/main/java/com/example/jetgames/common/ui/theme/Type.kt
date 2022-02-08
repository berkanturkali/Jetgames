package com.example.jetgames.common.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.jetgames.common.R

// Set of Material typography styles to start with
val Lexend = FontFamily(
    Font(R.font.lexend_extra_bold, weight = FontWeight.ExtraBold),
    Font(R.font.lexend_semi_bold, weight = FontWeight.SemiBold),
    Font(R.font.lexend_bold, weight = FontWeight.Bold),
    Font(R.font.lexend_medium, weight = FontWeight.Medium),
    Font(R.font.lexend_regular, weight = FontWeight.Normal),
    Font(R.font.lexend_thin, weight = FontWeight.Thin),
    Font(R.font.lexend_light, weight = FontWeight.Light),
    Font(R.font.lexend_extra_light, weight = FontWeight.ExtraLight)
)

val Typography = Typography(
    h1 = TextStyle(
        fontFamily = Lexend,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 54.sp
    ),
    h2 = TextStyle(
        fontFamily = Lexend,
        fontWeight = FontWeight.SemiBold,
        fontSize = 42.sp
    ),
    h3 = TextStyle(
        fontFamily = Lexend,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp
    ),
    h4 = TextStyle(
        fontFamily = Lexend,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp
    ),
    h5 = TextStyle(
        fontFamily = Lexend,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp
    ),
    h6 = TextStyle(
        fontFamily = Lexend,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = Lexend,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = Lexend,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    ),
    body1 = TextStyle(
        fontFamily = Lexend,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = Lexend,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    button = TextStyle(
        fontFamily = Lexend,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = Lexend,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )

)