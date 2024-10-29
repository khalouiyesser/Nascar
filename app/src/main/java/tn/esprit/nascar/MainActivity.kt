package tn.esprit.nascar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import tn.esprit.nascar.ui.theme.NascarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NascarTheme {
                val navController = rememberNavController()
                val showToolbar = remember { mutableStateOf(true) }
                Scaffold(modifier = Modifier.fillMaxSize(),


                    // hedha l app bar li mel fou9
                    topBar = { if (showToolbar.value) ToolbarComponent(navController) }) { innerPadding ->
                    NavHost(navController = navController, startDestination = "login", modifier = Modifier.padding(innerPadding)) {
                        composable(route = "login") {
                            showToolbar.value = false
                            LoginScreen(navController)
                        }
                        composable(route = "home") {
                            showToolbar.value = true
                            HomeScreen()
                        }
                        composable(route = "about") {
                            showToolbar.value = true
                            AboutScreen()
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MainPreview() {
    NascarTheme {
        HomeScreen()
    }
}

