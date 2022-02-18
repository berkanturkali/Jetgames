package com.example.jetgames.home.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import com.example.jetgames.common.R
import com.example.jetgames.common.ui.theme.JetgamesTheme
import com.example.jetgames.common.ui.theme.XLightGray
import com.example.jetgames.core.domain.model.games.*
import com.example.jetgames.home.rememberDominantColorState
import com.example.jetgames.home.verticalGradientScrim
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.shimmer

@Composable
fun GameGalleryItem(
    modifier: Modifier = Modifier,
    childModifier: Modifier = Modifier,
    game: Game,
    imageLoader: ImageLoader,
    isLoading: Boolean = false,
    onItemClick: (Int) -> Unit,
) {

    val dominantColorState = rememberDominantColorState()
    val backgroundColor by animateColorAsState(targetValue = dominantColorState.color,
        animationSpec = spring(stiffness = Spring.StiffnessLow))
    LaunchedEffect(game.backgroundImage) {
        if (game.backgroundImage != null) {
            dominantColorState.updateColorsFromImageUrl(game.backgroundImage!!)
        } else {
            dominantColorState.reset()
        }
    }

    Card(
        modifier = modifier.padding(8.dp),
        elevation = dimensionResource(id = R.dimen.dimen_8),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.dimen_16)),
        onClick = { onItemClick.invoke(game.id!!) }
    ) {
        Column(
            modifier = Modifier
                .verticalGradientScrim(backgroundColor)
        ) {

            //image
            GameImage(description = game.name,
                image = game.backgroundImage,
                imageLoader = imageLoader,
                isLoading = isLoading
            )

            Row(
                modifier = childModifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                //Platforms
                Platforms(platforms = game.parentPlatforms,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp))

                //Metascore
                if (game.metaCritic != null) {
                    MetaCritic(metaCritic = game.metaCritic!!,
                        ratingColor = game.calculateRgbFromMetacritic()!!,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
                        childModifier = Modifier.padding(horizontal = 12.dp, vertical = 2.dp),
                        isLoading = isLoading)
                }
            }

            //Name
            val rating = game.ratings?.maxByOrNull {
                it?.percent!!
            }
            Name(name = game.name, icon = rating?.icon, modifier = childModifier)

            //Release Date
            Row(
                modifier = childModifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ReleaseDate(released = game.released)
            }

            //Genres
            Row(
                modifier = childModifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Genres(genres = game.genres, color = backgroundColor)
            }

            //Rating Bar
            Row(
                modifier = childModifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                game.rating_top?.let {
                    RatingTop(rating = it)
                }
            }
        }
    }
}

@Composable
fun GameImage(
    modifier: Modifier = Modifier,
    description: String?,
    image: String?,
    imageLoader: ImageLoader,
    isLoading: Boolean = false,
) {
    val painter = rememberImagePainter(
        data = image,
        imageLoader = imageLoader,
        builder = { crossfade(true) }
    )

    Image(
        modifier = modifier
            .placeholder(visible = isLoading, highlight = PlaceholderHighlight.shimmer(XLightGray))
            .height(300.dp)
            .fillMaxWidth(),
        alignment = Alignment.Center,
        contentScale = ContentScale.Crop,
        painter = painter,
        contentDescription = description)
}

@Composable
fun Platforms(
    modifier: Modifier = Modifier,
    platforms: List<ParentPlatform?>? = null,
) {
    if (platforms != null) {
        val platformLogos = platformLogo(platforms = platforms)

        LazyRow(modifier = modifier) {
            items(items = platformLogos) { platformLogo ->
                PlatformLogoItem(platformImageResource = platformLogo, size = 21.dp)
            }
        }
    }
}

@Composable
fun MetaCritic(
    metaCritic: Int,
    ratingColor: Color,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 18.sp,
    childModifier: Modifier = Modifier,
    isLoading: Boolean,
) {
    Card(
        modifier = modifier,
        elevation = 8.dp,
        border = if (!isLoading) BorderStroke(1.dp, ratingColor) else BorderStroke(0.dp,
            Color.Transparent),
        shape = RoundedCornerShape(6.dp),
        backgroundColor = Color.Transparent,
    ) {
        Text(
            modifier = childModifier,
            text = metaCritic.toString(),
            fontSize = fontSize,
            style = MaterialTheme.typography.h3,
            maxLines = 1,
            textAlign = TextAlign.Center,
            color = ratingColor
        )
    }
}

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

@Composable
fun ReleaseDate(
    released: String?,
    size: TextUnit = 14.sp,
) {
    Text(
        modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
        text = stringResource(id = R.string.release_date),
        fontSize = size,
        style = MaterialTheme.typography.body1,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Start,
        color = MaterialTheme.colors.onPrimary
    )

    Text(
        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
        text = released ?: "",
        fontSize = size,
        style = MaterialTheme.typography.h5,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Start,
        color = MaterialTheme.colors.onPrimary
    )
}

@Composable
fun Genres(
    genres: List<Genre?>? = null,
    fontSize: TextUnit = 14.sp,
    color: Color,
) {
    Text(
        modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
        text = stringResource(id = R.string.genres),
        fontSize = fontSize,
        style = MaterialTheme.typography.body1,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Start,
        color = MaterialTheme.colors.onPrimary
    )

    Row(horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dimen_8)),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 12.dp)) {
        genres?.forEach { genre ->
            Text(
                modifier = Modifier
                    .shadow(elevation = dimensionResource(id = R.dimen.dimen_4),
                        shape = CircleShape,
                        clip = false)
                    .background(Brush.verticalGradient(listOf(color, color.copy(alpha = 0.6f))),
                        shape = CircleShape)
                    .padding(horizontal = dimensionResource(
                        id = R.dimen.dimen_8),
                        vertical = dimensionResource(id = R.dimen.dimen_4)),
                text = genre?.name.toString(),
                fontSize = fontSize,
                style = MaterialTheme.typography.h5,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colors.onPrimary
            )
        }
    }
}

@Composable
fun RatingTop(
    modifier: Modifier = Modifier,
    rating: Int,
    color: Color = Color.Yellow,
    fontSize: TextUnit = 14.sp,
) {
    Text(
        modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
        text = stringResource(id = R.string.rating),
        fontSize = fontSize,
        style = MaterialTheme.typography.body1,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Start,
        color = MaterialTheme.colors.onPrimary
    )
    RatingBar(color = color, rating = rating.toFloat(), modifier = modifier
        .height(16.dp)
        .padding(horizontal = 12.dp))
}

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Float,
    color: Color,
) {
    Row(modifier = modifier.wrapContentSize()) {
        (1..5).forEach { step ->
            val stepRating = when {
                rating > step -> 1f
                step.rem(rating) < 1 -> rating - (step - 1f)
                else -> 0f
            }
            RatingStar(stepRating, color)
        }
    }
}

@Composable
private fun RatingStar(
    rating: Float,
    ratingColor: Color = Color.Yellow,
    backgroundColor: Color = Color.Gray,
) {
    BoxWithConstraints(
        modifier = Modifier
            .wrapContentSize()
            .aspectRatio(1f)
            .clip(starShape)
    ) {
        Canvas(modifier = Modifier.size(maxHeight)) {
            drawRect(
                brush = SolidColor(backgroundColor),
                size = Size(
                    height = size.height * 1.4f,
                    width = size.width * 1.4f
                ),
                topLeft = Offset(
                    x = -(size.width * 0.1f),
                    y = -(size.height * 0.1f)
                )
            )
            if (rating > 0) {
                drawRect(
                    brush = SolidColor(ratingColor),
                    size = Size(
                        height = size.height * 1.1f,
                        width = size.width * rating
                    )
                )
            }
        }
    }
}

private val starShape = GenericShape { size, _ ->
    addPath(starPath(size.height))
}

private val starPath = { size: Float ->
    Path().apply {
        val outerRadius: Float = size / 1.8f
        val innerRadius: Double = outerRadius / 2.5
        var rot: Double = Math.PI / 2 * 3
        val cx: Float = size / 2
        val cy: Float = size / 20 * 11
        var x: Float = cx
        var y: Float = cy
        val step = Math.PI / 5

        moveTo(cx, cy - outerRadius)
        repeat(5) {
            x = (cx + Math.cos(rot) * outerRadius).toFloat()
            y = (cy + Math.sin(rot) * outerRadius).toFloat()
            lineTo(x, y)
            rot += step

            x = (cx + Math.cos(rot) * innerRadius).toFloat()
            y = (cy + Math.sin(rot) * innerRadius).toFloat()
            lineTo(x, y)
            rot += step
        }
        close()
    }
}

@Preview
@Composable
fun GameGalleryPrev() {
    JetgamesTheme {
        GameGalleryItem(game = Game(
            "https://media.rawg.io/media/games/456/456dea5e1c7e3cd07060c14e96612001.jpg",
            esrbRating = EsrbRating(4, "Mature"),
            genres = listOf(Genre(47792, 2, "", "Shooter")),
            id = 3498,
            metaCritic = 92,
            name = "The Witcher 3: Wild Hunt",
            parentPlatforms = listOf(ParentPlatform(Platform(1, "Pc"))),
            rating = 4.67,
            rating_top = 5,
            ratingsCount = 4986,
            released = "12 Dec 2021"
        ), imageLoader = LocalImageLoader.current) {}

    }
}

