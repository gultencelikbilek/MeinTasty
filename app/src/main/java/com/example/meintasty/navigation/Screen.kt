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
    data object BasketScreen : Screen("basket_screen")
    data object ProfileScreen : Screen("profile_screen")
    data object UpdateScreen : Screen("update_screen")
    data object PasswordScreen : Screen("password_screen")
    data object OrderScreen : Screen("order_screen")
    data object PaymentScreen : Screen("payment_screen")
    data object RestaurantLoginScreen : Screen("restaurant_login_screen")
    data object RestaurantProfileScreen : Screen("restaurant_profile_screen")
}