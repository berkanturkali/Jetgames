package com.example.jetgames.details.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.offset
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import com.example.jetgames.common.R
import com.example.jetgames.common.ui.theme.JetgamesTheme
import com.example.jetgames.common.ui.theme.LightGray
import com.example.jetgames.common.ui.theme.XLightGray
import com.example.jetgames.common.ui.theme.XXLightGray

@Composable
fun Description(
    modifier: Modifier = Modifier,
    description: String,
    minimizedMaxLines: Int = 3,
) {
    var isExpanded by remember { mutableStateOf(false) }
    val textLayoutResultState = remember { mutableStateOf<TextLayoutResult?>(null) }
    val seeMoreSizeState = remember { mutableStateOf<IntSize?>(null) }
    val seeMoreOffsetState = remember { mutableStateOf<Offset?>(null) }
    var cutDesc by remember(description) { mutableStateOf<String?>(null) }

    val textLayoutResult = textLayoutResultState.value
    val seeMoreSize = seeMoreSizeState.value
    val seeMoreOffset = seeMoreOffsetState.value

    LaunchedEffect(description, isExpanded, textLayoutResult, seeMoreSize) {
        val lastLineIndex = minimizedMaxLines - 1
        if (!isExpanded && textLayoutResult != null && seeMoreSize != null
            && lastLineIndex + 1 == textLayoutResult.lineCount
            && textLayoutResult.isLineEllipsized(lastLineIndex)
        ) {
            var lastCharIndex = textLayoutResult.getLineEnd(lastLineIndex, visibleEnd = true) + 1
            var charRect: Rect
            do {
                lastCharIndex -= 1
                charRect = textLayoutResult.getCursorRect(lastCharIndex)
            } while (
                charRect.left > textLayoutResult.size.width - seeMoreSize.width
            )
            seeMoreOffsetState.value = Offset(charRect.left, charRect.bottom - seeMoreSize.height)
            cutDesc = description.substring(startIndex = 0, endIndex = lastCharIndex)
        }
    }

    Text(text = cutDesc ?: description,
        maxLines = if (isExpanded) Int.MAX_VALUE else minimizedMaxLines,
        overflow = TextOverflow.Ellipsis,
        onTextLayout = { textLayoutResultState.value = it },
       style = MaterialTheme.typography.body1,
        color = MaterialTheme.colors.onPrimary)
    if (!isExpanded) {
        val density = LocalDensity.current
        Text(text = "... See more",
            color = LightGray,
            onTextLayout = { seeMoreSizeState.value = it.size },
            modifier = Modifier
                .then(
                    if (seeMoreOffset != null)
                        Modifier.offset(
                            x = with(density) { seeMoreOffset.x.toDp() },
                            y = with(density) { seeMoreOffset.y.toDp() },
                        )
                    else Modifier
                )
                .clickable {
                    isExpanded = true
                    cutDesc = null
                }
                .alpha(if (seeMoreOffset != null) 1f else 0f))
    }
}

@Preview
@Composable
fun DescriptionPrev() {
    JetgamesTheme {
        Description(description = stringResource(id = R.string.dummy_lorem))
    }
}