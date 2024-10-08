package com.example.meintasty.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.meintasty.feature.login_screen.LoginScreen
import com.example.meintasty.feature.signup_screen.SignUpScreen
import com.example.meintasty.feature.splash_screen.MeinTastySplashScreen
import com.example.meintasty.feature.canton_screen.CantonScreen
import com.example.meintasty.feature.choose_login_register.ChooseLoginRegister
import com.example.meintasty.feature.choose_login_register.ChooseLoginRegisterScreen
import com.example.meintasty.feature.search_screen.SearchScreen

@Composable
fun NavGraph() {

    val navController = rememberNavController()
    val context = LocalContext.current
    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(Screen.SplashScreen.route) {
            MeinTastySplashScreen(navController)
        }
        composable(Screen.CantonScreen.route) {
            CantonScreen(navController)
        }
        composable(Screen.LoginScreen.route) {
            Log.v("Logg:loginNav", "")
            LoginScreen(navController)
        }
        composable(Screen.SignUpScreen.route) {
            SignUpScreen(navController)
        }
        composable(Screen.ChooseLoginRegister.route) {
            ChooseLoginRegisterScreen(navController)
        }
        composable(Screen.SearchScreen.route) {
            SearchScreen()
        }
    }
}