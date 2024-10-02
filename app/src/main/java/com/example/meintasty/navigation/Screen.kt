package com.example.meintasty.navigation

sealed class Screen(val route : String) {
    object LoginScreen : Screen("login_screen")
    object SignUpScreen: Screen("sign_up_screen")
}