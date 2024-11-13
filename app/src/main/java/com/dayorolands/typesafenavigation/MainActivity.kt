package com.dayorolands.typesafenavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.dayorolands.typesafenavigation.ui.theme.TypeSafeNavigationTheme
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TypeSafeNavigationTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController, 
                    startDestination = ScreenA
                ) {
                    composable<ScreenA> { 
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Button(onClick = {
                                navController.navigate(
                                    ScreenB(
                                        name = "Ifedayo",
                                        age= 28
                                    )
                                )
                            }) {
                                Text(text = "Go to Screen B")
                            }
                        }
                    }

                    composable<ScreenB> {
                        val args = it.toRoute<ScreenB>()
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Button(onClick = { /*TODO*/ }) {
                                Text(text = "${args.name} and ${args.age} years old")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Serializable
object ScreenA

@Serializable
data class ScreenB(
    val name: String?,
    val age: Int
)