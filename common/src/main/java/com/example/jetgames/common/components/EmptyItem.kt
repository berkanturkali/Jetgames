package com.example.jetgames.common.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
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

@Composable
fun EmptyItem(
    modifier: Modifier = Modifier,
    query:String?
) {
    query?.let {
        Column(modifier = modifier.padding(dimensionResource(id = R.dimen.dimen_8)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.empty))
            LottieAnimation(
                modifier = Modifier.size(100.dp),
                composition = composition,
                iterations = LottieConstants.IterateForever)

            Text(
                text = String.format(stringResource(id = R.string.no_result_for), query),
                maxLines = 1,
                style = MaterialTheme.typography.subtitle2,
                color = Error,
                modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.dimen_16))
            )
        }
    }
}

@Preview
@Composable
fun EmptyItemPrev() {
    JetgamesTheme {
        EmptyItem(query = "query")
    }
}

