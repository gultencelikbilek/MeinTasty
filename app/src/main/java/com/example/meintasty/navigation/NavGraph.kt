package com.example.meintasty.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.meintasty.SplashScreenContent
import com.example.meintasty.feature.welcome_screen.NewScreen
import com.example.meintasty.feature.login_screen.LoginScreen
import com.example.meintasty.feature.signup_screen.SignUpScreen

@Composable
fun NavGraph() {

    val navController = rememberNavController()
    val context = LocalContext.current
    NavHost(navController = navController, startDestination = Screen.LoginScreen.route ){
        composable(Screen.LoginScreen.route){
            Log.v("Logg:loginNav","")
            LoginScreen(navController)
        }
        composable(Screen.SignUpScreen.route){
            SignUpScreen(navController)
        }
        composable(Screen.NewScreen.route){
            NewScreen()
        }
        composable(Screen.SplashScreenContent.route){
            SplashScreenContent(navController = navController)
        }
    }

}