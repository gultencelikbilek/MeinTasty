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
import com.example.meintasty.feature.choose_login_register.ChooseLoginRegisterScreen
import com.example.meintasty.feature.detail_restaurant.DetailRestaurantScreen
import com.example.meintasty.feature.restaurant_screen.RestaurantScreen

@Composable
fun NavGraph() {

    val navController = rememberNavController()
    val context = LocalContext.current
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
        composable(
            route = Screen.RestaurantScreen.route + "?selectedCityCode={selectedCityCode}",
            arguments = listOf(
                navArgument(name = "selectedCityCode") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            RestaurantScreen(
                cityCode = backStackEntry.arguments!!.getString("selectedCityCode"),
                navController = navController
                )
        }
        composable(
           route = Screen.DetailRestaurantScreen.route+"?restaurantId={restaurantId}",
            arguments = listOf(
                navArgument(name = "restaurantId"){
                    type = NavType.IntType
                }
            )
        ) {backStackEntry->

            DetailRestaurantScreen(
                restaurantId = backStackEntry.arguments?.getInt("restaurantId"),
                navController
            )
        }
    }
}