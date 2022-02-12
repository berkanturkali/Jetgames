package com.example.jetgames.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.jetgames.common.R
import com.example.jetgames.common.ui.theme.JetgamesTheme

@Composable
fun LoadingItem(
    modifier: Modifier = Modifier,
    childModifier: Modifier = Modifier,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
    Box(modifier = modifier
        .wrapContentHeight()) {
        LottieAnimation(modifier = childModifier
            .align(Alignment.Center)
            .size(125.dp),
            composition = composition,
            iterations = LottieConstants.IterateForever)
    }
}

@Preview
@Composable
private fun LoadingAnimationPreview() {
    JetgamesTheme {
            LoadingItem(modifier = Modifier
                .background(MaterialTheme.colors.primary)
                .fillMaxWidth())
    }
}