package com.example.meintasty.navigation

sealed class Screen(val route : String) {
    object LoginScreen : Screen("login_screen")
    object SignUpScreen: Screen("sign_up_screen")
    object NewScreen : Screen("new_screen")
    object SplashScreenContent : Screen("splash_screen_content")
}