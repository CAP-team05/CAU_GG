package com.example.caugg

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.caugg.feature.auth.signin.SignInScreen
import com.example.caugg.feature.auth.signup.SignUpScreen
import com.example.caugg.feature.home.HomeScreen
import com.example.caugg.feature.mastery.MasteryScreen
import com.example.caugg.feature.noticeboard.NoticeBoardScreen
import com.example.caugg.feature.noticeboard.WriteScreen

@Composable
fun MainApp() {
    Surface(modifier = Modifier.fillMaxSize()) {
        val navController = rememberNavController()
        val start = "login"
        NavHost(navController = navController, startDestination = start) {

            composable(route = "login") {
                SignInScreen(navController = navController)
            }
            composable(route = "signup") {
                SignUpScreen(navController = navController)
            }
            composable(route = "home") {
                HomeScreen(navController = navController)
            }
            composable(route = "mastery") {
                MasteryScreen(navController = navController)
            }
            composable(route = "noticeboard") {
                NoticeBoardScreen(navController = navController)
            }
            composable(route = "write") {
                WriteScreen(navController = navController)
            }
        }
    }
}
