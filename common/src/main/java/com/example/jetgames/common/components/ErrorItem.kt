package com.example.jetgames.common.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.jetgames.common.R
import com.example.jetgames.common.ui.theme.Error

@Composable
fun ErrorItem(
    message: String,
    modifier: Modifier = Modifier,
    onRetryClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.dimen_8)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.error))
        LottieAnimation(
            modifier = Modifier.size(50.dp),
            composition = composition,
            iterations = LottieConstants.IterateForever)
        Text(
            text = message,
            maxLines = 1,
            style = MaterialTheme.typography.subtitle2,
            color = Error,
            modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.dimen_16))
        )
        Button(onClick = onRetryClick,
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(backgroundColor = Error,
                contentColor = MaterialTheme.colors.onError)) {
            Text(text = stringResource(id = R.string.try_again))
        }
    }
}