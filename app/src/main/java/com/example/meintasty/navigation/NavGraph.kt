package com.example.meintasty.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.meintasty.feature.login_screen.LoginScreen
import com.example.meintasty.feature.signup_screen.SignUpScreen
import com.example.meintasty.feature.welcome_screen.CantonScreen

@Composable
fun NavGraph() {

    val navController = rememberNavController()
    val context = LocalContext.current
    NavHost(navController = navController, startDestination = Screen.WelcomeScreen.route ){
        composable(Screen.WelcomeScreen.route){
           CantonScreen(navController)
        }
        composable(Screen.LoginScreen.route){
            Log.v("Logg:loginNav","")
            LoginScreen(navController)
        }
        composable(Screen.SignUpScreen.route){
            SignUpScreen(navController)
        }
    }
}