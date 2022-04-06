package com.example.jetgames.filter.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.jetgames.common.R

@Composable
fun NavigationIcon(
    modifier: Modifier = Modifier,
    onNavigationClick: () -> Unit,
) {
    IconButton(
        onClick = { onNavigationClick.invoke() },
        modifier = modifier
    ) {
        Icon(
            tint = MaterialTheme.colors.onPrimary,
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = null
        )
    }
}
