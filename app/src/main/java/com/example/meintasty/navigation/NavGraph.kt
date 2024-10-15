package com.example.meintasty.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.meintasty.feature.login_screen.LoginScreen
import com.example.meintasty.feature.signup_screen.SignUpScreen
import com.example.meintasty.feature.splash_screen.MeinTastySplashScreen
import com.example.meintasty.feature.canton_screen.CantonScreen
import com.example.meintasty.feature.basket_screen.BasketScreen
import com.example.meintasty.feature.category_detail_screen.CategoryDetailScreen
import com.example.meintasty.feature.choose_login_register.ChooseLoginRegisterScreen
import com.example.meintasty.feature.detail_restaurant.DetailRestaurantScreen
import com.example.meintasty.feature.profile_screen.ProfileScreen
import com.example.meintasty.feature.restaurant_screen.RestaurantScreen

@Composable
fun NavGraph() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(Screen.SplashScreen.route) {
            MeinTastySplashScreen(navController)
        }
        composable(route = Screen.CantonScreen.route) {
            CantonScreen(navController)
        }
        composable(route = Screen.LoginScreen.route) {
            Log.v("Logg:loginNav", "")
            LoginScreen(navController)
        }
        composable(Screen.SignUpScreen.route) {
            SignUpScreen(navController)
        }
        composable(Screen.ChooseLoginRegisterScreen.route) {
            ChooseLoginRegisterScreen(navController)
        }
        composable(route = Screen.RestaurantScreen.route) {
            RestaurantScreen(
                navController = navController
            )
        }
        composable(
            route = Screen.DetailRestaurantScreen.route + "?restaurantId={restaurantId}",
            arguments = listOf(
                navArgument(name = "restaurantId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            DetailRestaurantScreen(
                restaurantId = backStackEntry.arguments?.getInt("restaurantId"),
                navController
            )
        }
        composable(
            route = Screen.CategoryDetailScreen.route + "?categoryId={categoryId}?categoryName={categoryName}",
            arguments = listOf(
                navArgument(name = "categoryId") {
                    type = NavType.IntType
                },
               navArgument(name = "categoryName") {
                    type = NavType.StringType
                }
            )
        ) { navBackStack ->
            CategoryDetailScreen(
                categoryId = navBackStack.arguments?.getInt("categoryId"),
                categoryName= navBackStack.arguments?.getString("categoryName"),
                navController
            )
        }
        composable(Screen.CartScreen.route) {
            BasketScreen(navController)
        }
        composable(Screen.ProfileScreen.route){
            ProfileScreen(navController = navController)
        }
    }
}