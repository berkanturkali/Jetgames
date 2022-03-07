package com.example.jetgames

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.navigation.plusAssign
import coil.ImageLoader
import com.example.jetgames.common.ui.theme.JetgamesTheme
import com.example.jetgames.home.viewmodel.HomeViewModel
import com.example.jetgames.navigation.graph.SetupNavGraph
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetgamesTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.primary) {
                    val navController = rememberAnimatedNavController()
                    val bottomSheetNavigator = rememberBottomSheetNavigator()

                    navController.navigatorProvider += bottomSheetNavigator
                    SetupNavGraph(navController = navController,
                        bottomSheetNavigator = bottomSheetNavigator,
                        imageLoader = imageLoader)
                }
            }
        }
    }
}