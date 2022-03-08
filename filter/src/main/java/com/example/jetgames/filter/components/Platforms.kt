package com.example.jetgames.filter.components

import android.annotation.SuppressLint
import androidx.compose.animation.core.*
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.jetgames.common.R.dimen
import com.example.jetgames.common.R.drawable
import com.example.jetgames.common.ui.theme.JetgamesTheme
import com.example.jetgames.core.domain.model.platforms.Platform

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun Platforms(
    modifier: Modifier = Modifier,
    platforms: List<Platform>,
) {

    var expanded by remember {
        mutableStateOf(false)
    }

    val transitionState = remember {
        MutableTransitionState(expanded).apply {
            targetState = !expanded
        }
    }

    val transition = updateTransition(targetState = transitionState, label = "")

    val arrowRotationDegree by transition.animateFloat({
        tween(durationMillis = 300)
    }, label = "") {
        if (expanded) 90f else 0f
    }
    Column(modifier = modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .clickable {
            expanded = !expanded
        }
        .padding(horizontal = dimensionResource(
            id = dimen.dimen_8))) {
        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val (title, arrow) = createRefs()
            Text(
                modifier = Modifier.constrainAs(title) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                },
                text = "Platform",
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.onPrimary)

            IconButton(
                modifier = Modifier.constrainAs(arrow) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
                content = {
                    Icon(
                        painter = painterResource(id = drawable.ic_next),
                        contentDescription = null,
                        modifier = Modifier.rotate(arrowRotationDegree),
                        tint = Color.White)
                },
                onClick = {/*TODO: navigate to platforms */ }
            )
        }

        val enterFadeIn = remember {
            fadeIn(
                animationSpec = TweenSpec(
                    durationMillis = 500,
                    easing = FastOutLinearInEasing
                )
            )
        }
        val enterExpand = remember {
            expandVertically(animationSpec = tween(500))
        }

        val exitFadeOut = remember {
            fadeOut(
                animationSpec = TweenSpec(
                    durationMillis = 500,
                    easing = LinearOutSlowInEasing
                )
            )
        }

        val exitCollapse = remember {
            shrinkVertically(animationSpec = tween(500))
        }
        androidx.compose.animation.AnimatedVisibility(visible = expanded,
            enter = enterFadeIn + enterExpand,
            exit = exitFadeOut + exitCollapse) {

            Column(modifier = Modifier.padding(horizontal = dimensionResource(id = dimen.dimen_8))) {
                platforms.forEach {
                    Platform(platform = it)
                }
            }
        }
    }
}

@Composable
fun Platform(
    modifier: Modifier = Modifier,
    platform: Platform,
) {
    Row(modifier = modifier
        .fillMaxWidth()
        .height(40.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = platform.name!!,
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onPrimary)

    }
}


@Preview
@Composable
fun PlatformsPrev() {
    JetgamesTheme {
        Platforms(platforms = listOf(
            Platform(id = 4, "Playstation 4"),
            Platform(id = 5, name = "Playstation 5")
        ))
    }
}