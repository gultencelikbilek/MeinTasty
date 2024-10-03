package com.example.meintasty.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.meintasty.feature.NewScreen
import com.example.meintasty.feature.login_screen.LoginScreen
import com.example.meintasty.feature.signup_screen.SignUpScreen

@Composable
fun NavGraph() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.LoginScreen.route ){
        composable(Screen.LoginScreen.route){
            LoginScreen(navController)
        }
        composable(Screen.SignUpScreen.route){
            SignUpScreen(navController)
        }
        composable(Screen.NewScreen.route){
            NewScreen()
        }
    }

}