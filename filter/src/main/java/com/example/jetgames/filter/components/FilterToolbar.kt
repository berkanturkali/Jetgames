package com.example.jetgames.filter.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetgames.common.R
import com.example.jetgames.common.components.noRippleClickable
import com.example.jetgames.common.ui.theme.JetgamesTheme
import com.example.jetgames.common.ui.theme.XXLightGray

@Composable
fun FilterToolbar(
    modifier: Modifier = Modifier,
    title: String,
    navigateUp: () -> Unit,
) {
    Box(
        modifier = modifier
            .background(MaterialTheme.colors.primary)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = null,
            modifier = Modifier
                .wrapContentHeight()
                .align(Alignment.CenterStart)
                .padding(dimensionResource(id = R.dimen.dimen_16))
                .noRippleClickable {
                    navigateUp()
                },
            tint = Color.White
        )
        Text(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp),
            textAlign = TextAlign.Center,
            text = title,
            color = Color.White,
            style = MaterialTheme.typography.h6,
        )

        Divider(
            thickness = 0.5.dp,
            color = XXLightGray.copy(alpha = 0.5f),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )
    }
}

@Preview
@Composable
fun ToolbarPrev() {
    JetgamesTheme {
        FilterToolbar(title = "Platforms", navigateUp = {})
    }
}
