package com.example.neshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.neshop.frontend.HomeScreen
import com.example.neshop.frontend.LoginForm
import com.example.neshop.frontend.RegisterForm
import com.example.neshop.ui.theme.NeshopTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Login"){
        composable("Login"){
            LoginForm(navController = navController)
        }
        composable("Register"){
            RegisterForm()
        }
        composable("HomeScreen"){
            HomeScreen(navController = navController)
        }
    }
}
