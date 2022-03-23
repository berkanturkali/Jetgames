package com.example.jetgames.common.util

import android.content.Context
import androidx.collection.LruCache
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import coil.Coil
import coil.request.ImageRequest
import coil.request.SuccessResult
import coil.size.Scale
import com.example.jetgames.common.ui.theme.Black
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.pow
import kotlin.math.sqrt


fun Modifier.verticalGradientScrim(
    color: Color,
): Modifier = composed {
    val colors = remember(color) {
        listOf(Black, color)
    }
    val brush = remember(color) {
        Brush.verticalGradient(
            colors = colors,
        )
    }
    drawBehind {
        drawRect(brush = brush)
    }
}

fun Modifier.gradientBackground(color: Color, angle: Float) = this.then(
    Modifier.drawBehind {
        val angleRad = angle / 180f * Math.PI
        val x = kotlin.math.cos(angleRad).toFloat()
        val y = kotlin.math.sin(angleRad.toFloat())

        val radius = sqrt(size.width.pow(2) + size.height.pow(2)) / 2f
        val offset = center + Offset(x * radius, y * radius)

        val exactOffset = Offset(
            x = Math.min(offset.x.coerceAtLeast(0f), size.width),
            y = size.height - Math.min(offset.y.coerceAtLeast(0f), size.height)
        )
        drawRect(
            brush = Brush.linearGradient(
                colors = listOf(color.copy(alpha = 0f), color),
                start = Offset(size.width, size.height) - exactOffset,
                end = exactOffset,
            ),
            size = size
        )
    }
)

@Composable
fun rememberDominantColorState(
    context: Context = LocalContext.current,
    defaultColor: Color = MaterialTheme.colors.primary,
    defaultOnColor: Color = MaterialTheme.colors.onPrimary,
    cacheSize: Int = 12,
): DominantColorState = remember {
    DominantColorState(context, defaultColor, defaultOnColor, cacheSize)
}

@Stable
class DominantColorState(
    private val context: Context,
    private val defaultColor: Color,
    private val defaultOnColor: Color,
    cacheSize: Int = 12,
) {
    var color by mutableStateOf(defaultColor)
        private set
    var onColor by mutableStateOf(defaultOnColor)
        private set

    private val cache = when {
        cacheSize > 0 -> LruCache<String, DominantColors>(cacheSize)
        else -> null
    }

    suspend fun updateColorsFromImageUrl(url: String) {
        val result = calculateDominantColor(url)
        color = result?.color ?: defaultColor
        onColor = result?.onColor ?: defaultOnColor
    }

    private suspend fun calculateDominantColor(url: String): DominantColors? {
        val cached = cache?.get(url)
        if (cached != null) {
            return cached
        }
        return calculateDominantSwatchInImage(context, url)?.let { swatch ->
            DominantColors(
                color = Color(swatch.rgb),
                onColor = Color(swatch.titleTextColor).copy(alpha = 1f)
            )
        }
            ?.also { result -> cache?.put(url, result) }
    }

    fun reset() {
        color = defaultColor
        onColor = defaultColor
    }
}

private suspend fun calculateDominantSwatchInImage(
    context: Context,
    imageUrl: String,
): Palette.Swatch? {
    val r = ImageRequest.Builder(context)
        .data(imageUrl)
        .size(128).scale(Scale.FILL)
        .allowHardware(false)
        .build()

    val bitmap = when (val result = Coil.execute(r)) {
        is SuccessResult -> result.drawable.toBitmap()
        else -> null
    }


    return withContext(Dispatchers.Default) {
        bitmap?.let {
            val palette = Palette.Builder(it)
                .resizeBitmapArea(0)
                .clearFilters()
                .maximumColorCount(8)
                .generate()

            palette.dominantSwatch
        }
    }
}


@Immutable
private data class DominantColors(val color: Color, val onColor: Color)


