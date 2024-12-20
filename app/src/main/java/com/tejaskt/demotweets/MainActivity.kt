package com.tejaskt.demotweets

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tejaskt.demotweets.screens.CategoryScreen
import com.tejaskt.demotweets.screens.DetailScreen
import com.tejaskt.demotweets.ui.theme.DemoTweetsTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DemoTweetsTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = "Demo Tweets")
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color.Black, // Replaces backgroundColor
                                titleContentColor = Color.White // Replaces contentColor
                            )
                        )
                    }
                ) { paddingValues ->
                    Box(modifier = Modifier.padding(paddingValues)) {
                        App()
                    }
                }
            }
        }
    }
}


@Composable
fun App(){
    val navController = rememberNavController() // controller
    NavHost(navController = navController, startDestination = "category"){ // navHost provide the area where screen will render

        // this are the nodes with in a graph that define the path
        composable(route = "category"){
            CategoryScreen{
                navController.navigate("detail/${Uri.encode(it)}")
            }
        }

        composable(
            route = "detail/{category}", // for more than one argument use /{arg1}/{arg2}/{arg3}...
            arguments = listOf(
                navArgument("category"){
                    type = NavType.StringType // this will define the type of the argument passed
                }
            )
            ){
            DetailScreen()
        }
    }
}
