package com.example.jetgames.filter.components

import androidx.compose.animation.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.example.jetgames.common.R

@Composable
fun ApplyFilterFab(
    modifier: Modifier = Modifier,
    onButtonClick: (() -> Unit)? = null
) {
    FloatingActionButton(
        onClick = { onButtonClick?.invoke() },
        modifier = modifier
            .wrapContentSize(align = Alignment.BottomEnd, unbounded = true)
            .padding(
                dimensionResource(id = R.dimen.dimen_32)
            )
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_apply),
            contentDescription = "Apply Filters",
            tint = Color.White
        )
    }
}
