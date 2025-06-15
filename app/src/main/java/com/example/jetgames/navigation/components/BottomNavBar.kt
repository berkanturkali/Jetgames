package com.example.jetgames.navigation.components

import androidx.annotation.FloatRange
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.jetgames.common.JetgamesSurface
import com.example.jetgames.navigation.BottomNavigationItem

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {

    val items = listOf(
        BottomNavigationItem.HomeScreen,
        BottomNavigationItem.FavoritesScreen,
    )

    val backStackEntry = navController.currentBackStackEntryAsState()

    val selected = items.firstOrNull {
        it.route::class.qualifiedName == backStackEntry.value?.destination?.route
    }

    val routes = remember { items.map { it.route } }
    JetgamesSurface(
        modifier = modifier
    ) {
        Divider()
        val springSpec = SpringSpec<Float>(
            stiffness = 800f,
            dampingRatio = 0.8f
        )

        JetgamesBottomNavLayout(
            selectedIndex = items.indexOf(selected),
            itemCount = routes.size,
            animSpec = springSpec,
            indicator = { JetgamesBottomNavIndicator() },
        ) {
            items.forEach { item ->
                val tint by animateColorAsState(
                    if (item.route == selected?.route) {
                        MaterialTheme.colors.onPrimary
                    } else {
                        MaterialTheme.colors.onPrimary.copy(0.1f)
                    }
                )
                JetgamesBottomNavigationItem(
                    icon = {
                        Icon(
                            tint = tint,
                            painter = painterResource(id = item.icon),
                            contentDescription = item.title
                        )
                    },
                    text = {
                        Text(
                            text = item.title,
                            textAlign = TextAlign.Center,
                            fontSize = 10.sp,
                            color = MaterialTheme.colors.onPrimary
                        )
                    },
                    selected = item.route == selected?.route,
                    onSelected = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true

                            restoreState = true
                        }
                    },
                    animSpec = springSpec,
                    modifier = BottomNavigationItemPadding.clip(BottomNavIndicatorShape)
                )
            }
        }
    }
}

@Composable
private fun JetgamesBottomNavLayout(
    modifier: Modifier = Modifier,
    itemCount: Int,
    selectedIndex: Int,
    indicator: @Composable BoxScope.() -> Unit,
    animSpec: AnimationSpec<Float>,
    content: @Composable () -> Unit,
) {

    val selectionFractions = remember(itemCount) {
        List(itemCount) { i ->
            Animatable(if (i == selectedIndex) 1f else 0f)
        }
    }

    selectionFractions.forEachIndexed { index, selectionFraction ->
        val target = if (index == selectedIndex) 1f else 0f
        LaunchedEffect(target, animSpec) {
            selectionFraction.animateTo(target, animSpec)
        }
    }

    val indicatorIndex = remember { Animatable(0f) }
    val targetIndicatorIndex = selectedIndex.toFloat()
    LaunchedEffect(targetIndicatorIndex) {
        indicatorIndex.animateTo(targetIndicatorIndex, animSpec)
    }

    Layout(
        modifier = modifier.height(BottomNavHeight),
        content = {
            content()
            Box(Modifier.layoutId("indicator"), content = indicator)
        },
    ) { measurables, constraints ->

        val unselectedWidth = constraints.maxWidth / itemCount
        val indicatorMeasurable = measurables.first { it.layoutId == "indicator" }

        val itemPlaceables = measurables
            .filterNot { it == indicatorMeasurable }
            .mapIndexed { index, measurable ->
                val width = lerp(unselectedWidth, unselectedWidth, selectionFractions[index].value)
                measurable.measure(
                    constraints.copy(
                        minWidth = width,
                        maxWidth = width
                    )
                )
            }

        val indicatorPlaceable = indicatorMeasurable.measure(
            constraints.copy(
                minWidth = unselectedWidth,
                maxWidth = unselectedWidth
            )
        )

        layout(
            width = constraints.maxWidth,
            height = itemPlaceables.maxByOrNull { it.height }?.height ?: 0
        ) {
            val indicatorLeft = indicatorIndex.value * unselectedWidth
            indicatorPlaceable.placeRelative(x = indicatorLeft.toInt(), y = 0)
            var x = 0
            itemPlaceables.forEach { placeable ->
                placeable.placeRelative(x = x, y = 0)
                x += placeable.width
            }
        }
    }
}

@Composable
fun JetgamesBottomNavigationItem(
    icon: @Composable BoxScope.() -> Unit,
    text: @Composable BoxScope.() -> Unit,
    selected: Boolean,
    onSelected: () -> Unit,
    animSpec: AnimationSpec<Float>,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.selectable(selected = selected, onClick = onSelected),
        contentAlignment = Alignment.Center
    ) {
        val animationProgress by animateFloatAsState(if (selected) 1f else 0f, animSpec)
        JetgamesBottomNavItemLayout(
            icon = icon,
            text = text,
            animationProgress = animationProgress
        )
    }
}

@Composable
private fun JetgamesBottomNavItemLayout(
    icon: @Composable BoxScope.() -> Unit,
    text: @Composable BoxScope.() -> Unit,
    @FloatRange(from = 0.0, to = 1.0) animationProgress: Float,
) {
    Layout(
        content = {
            Box(
                modifier = Modifier
                    .layoutId("icon")
                    .padding(horizontal = TextIconSpacing),
                content = icon
            )

            val scale = lerp(0.6f, 1f, animationProgress)
            Box(
                modifier = Modifier
                    .layoutId("text")
                    .padding(horizontal = TextIconSpacing)
                    .graphicsLayer {
                        alpha = animationProgress
                        scaleX = scale
                        scaleY = scale
                        transformOrigin = BottomNavLabelTransformOrigin
                    },
                content = text
            )
        }
    ) { measurables, constraints ->
        val iconPlaceable = measurables.first { it.layoutId == "icon" }.measure(constraints)
        val textPlaceable = measurables.first { it.layoutId == "text" }.measure(constraints)

        placeTextAndIcon(
            textPlaceable,
            iconPlaceable,
            constraints.maxWidth,
            constraints.maxHeight,
            animationProgress = animationProgress
        )
    }
}

private fun MeasureScope.placeTextAndIcon(
    textPlaceable: Placeable,
    iconPlaceable: Placeable,
    width: Int,
    height: Int,
    @FloatRange(from = 0.0, to = 1.0) animationProgress: Float,
): MeasureResult {

    val iconY = (height - iconPlaceable.height) / 2
    val textY = (height - textPlaceable.height) / 2

    val textWidth = textPlaceable.width * animationProgress
    val iconX = (width - textWidth - iconPlaceable.width) / 2
    val textX = iconX + iconPlaceable.width

    return layout(width, height = height) {
        iconPlaceable.placeRelative(iconX.toInt(), iconY)
        if (animationProgress != 0f) {
            textPlaceable.placeRelative(textX.toInt(), textY)
        }
    }
}

@Composable
private fun JetgamesBottomNavIndicator(
    strokeWidth: Dp = 1.dp,
    color: Color = MaterialTheme.colors.onSurface.copy(0.12f),
    shape: Shape = BottomNavIndicatorShape,
) {
    Spacer(
        modifier = Modifier
            .fillMaxSize()
            .then(BottomNavigationItemPadding)
            .border(strokeWidth, color, shape)
    )
}

private val TextIconSpacing = 2.dp
private val BottomNavHeight = 56.dp
private val BottomNavLabelTransformOrigin = TransformOrigin(0f, 0.5f)
private val BottomNavIndicatorShape = RoundedCornerShape(percent = 50)
private val BottomNavigationItemPadding = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
