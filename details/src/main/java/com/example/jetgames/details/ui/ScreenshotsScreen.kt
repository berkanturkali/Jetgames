package com.example.jetgames.details.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.compose.rememberImagePainter
import com.example.jetgames.details.viewmodel.ScreenshotsViewModel
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlin.math.absoluteValue

@Composable
fun ScreenshotsScreen(
    modifier: Modifier = Modifier,
    viewModel: ScreenshotsViewModel = hiltViewModel(),
    imageLoader: ImageLoader
) {

    val screenshotsAndPage by viewModel.screenshotsAndPage.observeAsState()

    val pagerState = rememberPagerState(initialPage = screenshotsAndPage?.selectedPage ?: 0)

    // screenshots
    HorizontalPager(screenshotsAndPage?.screenshots!!.size, state = pagerState) { page ->
        Card(
            modifier = modifier
                .graphicsLayer {
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                    lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
                .fillMaxSize()
                .aspectRatio(1f)
        ) {
            Box {
                Image(
                    painter = rememberImagePainter(
                        data = screenshotsAndPage?.screenshots!!.get(page),
                        imageLoader = imageLoader
                    ),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}
