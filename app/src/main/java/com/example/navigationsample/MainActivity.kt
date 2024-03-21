package com.example.navigationsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigationsample.ui.theme.NavigationSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Code here
                    MyApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "firstscreen", builder ={
        composable("firstscreen"){
            FirstScreen {name,age->
                navController.navigate("secondscreen/$name&$age")
            }
        }
        composable("secondscreen/{name}{age}"){
            val name = it.arguments?.getString("name") ?: "No Name"
            val age = it.arguments?.getInt("age") ?: 0
            SecondScreen(name,age) {
                navController.navigate("third_screen")
            }
        }
        composable("third_screen"){
            ThirdScreen {
                navController.navigate("firstscreen")
            }
        }
    } )

}