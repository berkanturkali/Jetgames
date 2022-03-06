package com.example.jetgames.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetgames.common.R

@Composable
fun Name(
    modifier: Modifier = Modifier,
    name: String?,
    icon: String?,
    size: TextUnit = 24.sp,
) {
    val id = "inlineContent"
    val text = buildAnnotatedString {
        name?.let {
            append(it)
        }
        appendInlineContent(id, "[icon]")
    }

    val content = mapOf(
        Pair(
            id,
            InlineTextContent(
                Placeholder(width = 20.sp,
                    height = 20.sp,
                    placeholderVerticalAlign = PlaceholderVerticalAlign.Center)
            ) {
                icon?.let {
                    Text(text = icon)
                }
            }
        )
    )
    Text(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 12.dp,
                vertical = dimensionResource(
                    id = R.dimen.dimen_8)),
        text = text,
        inlineContent = content,
        fontSize = size,
        maxLines = 2,
        style = MaterialTheme.typography.h3,
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Start,
        color = MaterialTheme.colors.onPrimary
    )
}