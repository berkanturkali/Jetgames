package com.example.jetgames.details.components

import android.annotation.SuppressLint
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetgames.common.ui.theme.JetgamesTheme

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
internal fun Toolbar(
    modifier: Modifier = Modifier,
    isLiked: Boolean,
    onBackButtonClick: () -> Unit,
    onFavButtonClick: (Boolean) -> Unit,
) {

    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.background(brush = Brush.verticalGradient(listOf(MaterialTheme.colors.primary,
            MaterialTheme.colors.primary.copy(0f))))) {

        Row(modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = com.example.jetgames.common.R.dimen.dimen_16),
                vertical = dimensionResource(
                    id = com.example.jetgames.common.R.dimen.dimen_8)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Surface(
                modifier = Modifier
                    .size(35.dp),
                shape = CircleShape,
                color = MaterialTheme.colors.onPrimary.copy(alpha = 0.1f),
                contentColor = MaterialTheme.colors.onPrimary
            ) {
                IconButton(onClick = onBackButtonClick) {
                    Icon(
                        painter = painterResource(id = com.example.jetgames.common.R.drawable.ic_back),
                        contentDescription = null
                    )
                }
            }

            IconToggleButton(
                checked = isLiked,
                onCheckedChange = {
                    onFavButtonClick.invoke(it)
                }
            ) {


                val transition = updateTransition(isLiked, label = null)

                val tint by transition.animateColor(label = "Tint") {
                    if (it) Color.Yellow else MaterialTheme.colors.onPrimary
                }

                val size by transition.animateDp(
                    transitionSpec = {
                        if (false isTransitioningTo true) {
                            keyframes {
                                durationMillis = 250
                                30.dp at 0 with LinearOutSlowInEasing
                                35.dp at 15 with FastOutLinearInEasing
                                40.dp at 75
                                35.dp at 150
                            }
                        } else {
                            spring(stiffness = Spring.StiffnessVeryLow)
                        }
                    },
                    label = "Size"
                ) {
                    30.dp
                }

                Icon(
                    painter = if (isLiked) painterResource(id = com.example.jetgames.common.R.drawable.ic_filled_star) else painterResource(
                        id = com.example.jetgames.common.R.drawable.ic_star),
                    contentDescription = null,
                    tint = tint,
                    modifier = Modifier.size(size)
                )
            }
        }
    }
}

@Preview
@Composable
fun ToolbarPrev() {
    JetgamesTheme {
        Toolbar(onBackButtonClick = { /*TODO*/ }, isLiked = false) {

        }
    }
}