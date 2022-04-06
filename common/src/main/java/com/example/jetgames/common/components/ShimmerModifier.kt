package com.example.jetgames.common.components

import androidx.compose.ui.Modifier
import com.example.jetgames.common.ui.theme.XXLightGray
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.shimmer

val ShimmerModifier = Modifier.placeholder(
    visible = true,
    highlight = PlaceholderHighlight.shimmer(
        XXLightGray
    )
)
