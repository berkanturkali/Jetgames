package com.example.jetgames.details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberImagePainter
import com.example.jetgames.common.R
import com.example.jetgames.details.Mode
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@Composable
fun DetailScreenImageSection(
    modifier: Modifier = Modifier,
    childModifier: Modifier = Modifier,
    imageUrl: String? = null,
    imageLoader: ImageLoader,
    description: String? = null,
    screenshots: List<String?>?,
    isScreenShotsVisible: Boolean = false,
) {
    val pagerState = rememberPagerState()
    val mode = rememberSaveable {
        mutableStateOf(Mode.IMAGE)
    }

    Card(
        modifier = modifier.fillMaxSize()
    ) {
        val painter = rememberImagePainter(
            data = imageUrl,
            imageLoader = imageLoader
        )
        Box() {
            when (mode.value) {
                Mode.IMAGE -> {
                    Image(
                        modifier = childModifier
                            .fillMaxWidth()
                            .height(350.dp),
                        alignment = Alignment.Center,
                        contentScale = ContentScale.Crop,
                        painter = painter,
                        contentDescription = description
                    )
                }
                Mode.SCREENSHOTS -> {
                    HorizontalPager(count = screenshots!!.size,
                        state = pagerState) { page ->
                        val pagerPainter = rememberImagePainter(data = screenshots[page],
                            imageLoader = imageLoader)
                        Image(
                            modifier = childModifier
                                .fillMaxWidth()
                                .height(350.dp),
                            alignment = Alignment.Center,
                            contentScale = ContentScale.Crop,
                            painter = pagerPainter,
                            contentDescription = description
                        )
                    }
                    HorizontalPagerIndicator(
                        pagerState = pagerState,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(16.dp),
                    )

                }
            }

            Box(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Brush.verticalGradient(listOf(MaterialTheme.colors.primary,
                    MaterialTheme.colors.primary.copy(alpha = 0.1f)))))
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(id = R.dimen.dimen_8),
                    vertical = dimensionResource(
                        id = R.dimen.dimen_8)), horizontalAlignment = Alignment.End) {
                Row(modifier = Modifier, horizontalArrangement = Arrangement.spacedBy(
                    dimensionResource(id = R.dimen.dimen_16))) {
                    //game image
                    Icon(painter = painterResource(id = R.drawable.ic_image),
                        description,
                        tint = MaterialTheme.colors.onPrimary,
                        modifier = Modifier.clickable { mode.value = Mode.IMAGE })
                    //screenshots
                    if (isScreenShotsVisible) {
                        Icon(painter = painterResource(id = R.drawable.ic_screenshots),
                            null,
                            tint = MaterialTheme.colors.onPrimary,
                            modifier = Modifier.clickable { mode.value = Mode.SCREENSHOTS })
                    }
                }
            }
        }
    }
}