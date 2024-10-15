package com.example.meintasty.navigation

sealed class Screen(val route : String) {
    data object LoginScreen : Screen("login_screen")
    data object SignUpScreen: Screen("sign_up_screen")
    data object NewScreen : Screen("new_screen")
    data object CantonScreen : Screen("welcome_screen")
    data object SplashScreen : Screen("splash_screen")
    data object ChooseLoginRegisterScreen : Screen("choose_login_register")
    data object RestaurantScreen : Screen("restaurant_screen")
    data object DetailRestaurantScreen : Screen("detail_restaurant_screen")
    data object CategoryDetailScreen : Screen("category_detail_screen")
    data object CartScreen : Screen("cart_screen")
    data object ProfileScreen : Screen("profile_screen")

}