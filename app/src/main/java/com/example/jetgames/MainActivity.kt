package com.example.jetgames

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.navigation.plusAssign
import com.example.jetgames.common.ui.theme.JetgamesTheme
import com.example.jetgames.navigation.graph.SetupNavGraph
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetgamesTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    val bottomSheetNavigator = rememberBottomSheetNavigator()

                    navController.navigatorProvider += bottomSheetNavigator
                    SetupNavGraph(navController = navController,
                        bottomSheetNavigator = bottomSheetNavigator)
                }
            }
        }
    }
}