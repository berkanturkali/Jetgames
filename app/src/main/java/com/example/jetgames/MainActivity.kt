package com.example.jetgames

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
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
        setContent {
            JetgamesTheme {
                val navController = rememberNavController()

                val mainActivityViewModel: MainActivityViewModel = hiltViewModel()

                navController.addOnDestinationChangedListener { _, destination, _ ->
                    mainActivityViewModel.setIsNavbarVisible(destination)
                }

                Scaffold(
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
