package com.example.jetgames.common.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.jetgames.common.R
import com.example.jetgames.common.ui.theme.Error
import com.example.jetgames.common.ui.theme.JetgamesTheme
import com.example.jetgames.common.ui.theme.XLightGray

@Composable
fun LottieEmptyScreen(
    modifier: Modifier = Modifier,
    message: String = stringResource(id = R.string.nothing_here),
) {

    Column(
        modifier = modifier.padding(dimensionResource(id = R.dimen.dimen_8)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.empty))
        LottieAnimation(
            modifier = Modifier.size(100.dp),
            composition = composition,
            iterations = LottieConstants.IterateForever
        )

        ErrorMessage(message = message, color = Error)
    }
}

@Composable
fun ResourceEmptyScreen(
    modifier: Modifier = Modifier,
    message: String = stringResource(id = R.string.nothing_here),
    @DrawableRes drawable: Int = R.drawable.ic_bad,
) {
    Column(
        modifier = modifier.padding(dimensionResource(id = R.dimen.dimen_8)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(drawable),
            contentDescription = null,
            tint = XLightGray.copy(alpha = 0.6f)
        )

        ErrorMessage(message = message)
    }
}

@Composable
private fun ErrorMessage(
    message: String,
    color: Color = XLightGray.copy(alpha = 0.6f),
) {
    Text(
        text = message,
        maxLines = 1,
        style = MaterialTheme.typography.subtitle2,
        color = color,
        modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.dimen_16))
    )
}

@Preview
@Composable
fun ResEmptyScreenPrev() {
    JetgamesTheme {
        ResourceEmptyScreen()
    }
}
