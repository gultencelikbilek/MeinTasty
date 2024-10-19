package com.example.meintasty.navigation

import android.util.Log
import androidx.compose.runtime.Composable
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
import com.example.meintasty.feature.password_screen.PasswordScreen
import com.example.meintasty.feature.profile_screen.ProfileScreen
import com.example.meintasty.feature.restaurant_screen.RestaurantScreen
import com.example.meintasty.feature.update_screen.UpdateScreen

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
        composable(Screen.BasketScreen.route) {
            BasketScreen(navController)
        }
        composable(Screen.ProfileScreen.route){
            ProfileScreen(navController = navController)
        }
        composable(
          route =  Screen.UpdateScreen.route+"?userId={userId}?email={email}?phone={phone}?updateType={updateType}",
            arguments = listOf(
                navArgument(name = "userId"){
                    type = NavType.IntType
                },
                navArgument(name = "phone"){
                    type = NavType.StringType
                },
                navArgument(name = "email"){
                    type = NavType.StringType
                },
                navArgument(name = "updateType"){
                    type = NavType.StringType
                }
            ),

        ){navBackStack ->
            UpdateScreen(
                userId = navBackStack.arguments?.getInt("userId"),
                email = navBackStack.arguments?.getString("phone"),
                phoneNum = navBackStack.arguments?.getString("email"),
                updateType = navBackStack.arguments?.getString("updateType"),
                navController
            )
        }
        composable(
            route=Screen.PasswordScreen.route+"?userId={userId}",
            arguments = listOf(
                navArgument(name = "userId"){
                    type = NavType.IntType
                }
            )
        ){navBackStack->
            PasswordScreen(
                userId = navBackStack.arguments?.getInt("userId"),
                navController = navController
            )
        }
    }
}