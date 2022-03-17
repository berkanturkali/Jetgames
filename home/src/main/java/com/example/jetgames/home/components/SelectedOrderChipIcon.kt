package com.example.jetgames.home.components

import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun SelectedOrderChipIcon(
    modifier: Modifier = Modifier,
) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = com.example.jetgames.common.R.drawable.ic_apply),
        contentDescription = null)
}