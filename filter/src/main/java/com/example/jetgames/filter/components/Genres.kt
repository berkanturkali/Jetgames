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
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.jetgames.common.R
import com.example.jetgames.core.domain.model.games.Genre

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun Genres(
    modifier: Modifier = Modifier,
    genres: List<Genre>? = null,
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
            id = R.dimen.dimen_8))) {

        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val (title, arrow) = createRefs()
            Text(
                modifier = Modifier.constrainAs(title) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                },
                text = "Genres",
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
                        painter = painterResource(id = R.drawable.ic_next),
                        contentDescription = null,
                        modifier = Modifier.rotate(arrowRotationDegree),
                        tint = Color.White)
                },
                onClick = {/*TODO: navigate to genres */ }
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
            Column(modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.dimen_8))) {
                genres?.let {
                    if (it.isNotEmpty()) {
                        it.forEach {
                            Genre(genre = it)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Genre(
    modifier: Modifier = Modifier,
    genre: Genre,
) {
    Row(modifier = modifier
        .fillMaxWidth()
        .height(40.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = genre.name!!,
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onPrimary)

    }
}