package com.example.meintasty.navigation

sealed class Screen(val route : String) {
    object LoginScreen : Screen("login_screen")
    object SignUpScreen: Screen("sign_up_screen")
    object NewScreen : Screen("new_screen")
    object CantonScreen : Screen("welcome_screen")
    object SplashScreen : Screen("splash_screen")
    object ChooseLoginRegister : Screen("choose_login_register")
    object SearchScreen : Screen("search_screen")

}