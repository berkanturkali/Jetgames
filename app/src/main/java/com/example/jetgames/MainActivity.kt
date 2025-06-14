package com.example.jetgames

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import com.example.jetgames.common.ui.theme.JetgamesTheme
import com.example.jetgames.navigation.MainActivityViewModel
import com.example.jetgames.navigation.components.BottomNavBar
import com.example.jetgames.navigation.graph.JetGamesNavGraph
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetgamesTheme {
                val navController = rememberNavController()

                val mainActivityViewModel: MainActivityViewModel = hiltViewModel()

                navController.addOnDestinationChangedListener { _, destination, _ ->
                    mainActivityViewModel.setIsNavbarVisible(destination)
                }

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colors.background)
                        .windowInsetsPadding(
                            WindowInsets.Companion.systemBars
                        ),
                    bottomBar = {
                        if (mainActivityViewModel.isNavbarVisible)
                            BottomNavBar(navController = navController)
                    }
                ) { padding ->
                    /*
                     * https://stackoverflow.com/a/66574166/11943929
                     */
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding)
                    ) {
                        JetGamesNavGraph(
                            navController = navController,
                            imageLoader = imageLoader,
                        )
                    }
                }
            }
        }
    }
}
