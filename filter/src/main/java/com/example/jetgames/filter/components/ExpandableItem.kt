package com.example.jetgames.filter.components

import android.annotation.SuppressLint
import androidx.compose.animation.core.*
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.jetgames.common.R

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun ExpandableItem(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    sectionTitle: String,
    onColumnClick: () -> Unit = {},
    onSectionClick: (String) -> Unit = {},
    screenRoute: String,
    content: @Composable () -> Unit = {},
) {

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

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onColumnClick.invoke() }
            .padding(
                horizontal = dimensionResource(
                    id = R.dimen.dimen_8
                )
            )
    ) {

        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val (title, arrow) = createRefs()

            // title
            Text(
                modifier = Modifier
                    .constrainAs(title) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    }
                    .padding(start = dimensionResource(id = R.dimen.dimen_8)),
                text = sectionTitle,
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.onPrimary
            )

            // expand/navigate button
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
                        tint = Color.White
                    )
                },
                onClick = { onSectionClick(screenRoute) }
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

        androidx.compose.animation.AnimatedVisibility(
            visible = expanded,
            enter = enterFadeIn + enterExpand,
            exit = exitFadeOut + exitCollapse
        ) {
            content()
        }
    }
}
