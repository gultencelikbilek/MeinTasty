package com.example.meintasty.navigation

sealed class Screen(val route : String) {
    object LoginScreen : Screen("login_screen")
    object SignUpScreen: Screen("sign_up_screen")
    object NewScreen : Screen("new_screen")
    object CantonScreen : Screen("welcome_screen")
    object SplashScreen : Screen("splash_screen")
    object ChooseLoginRegisterScreen : Screen("choose_login_register")
    object RestaurantScreen : Screen("search_screen")
    object DetailRestaurantScreen : Screen("detail_restaurant_screen")

}