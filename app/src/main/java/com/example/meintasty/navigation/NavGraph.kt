package com.example.meintasty.navigation

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.meintasty.feature.user_feature.user_login_screen.LoginScreen
import com.example.meintasty.feature.user_feature.signup_screen.SignUpScreen
import com.example.meintasty.feature.common_screen_feature.splash_screen.MeinTastySplashScreen
import com.example.meintasty.feature.common_screen_feature.canton_screen.CantonScreen
import com.example.meintasty.feature.user_feature.basket_screen.BasketScreen
import com.example.meintasty.feature.user_feature.category_detail_screen.CategoryDetailScreen
import com.example.meintasty.feature.common_screen_feature.choose_login_register.ChooseLoginRegisterScreen
import com.example.meintasty.feature.restaurant_feature.restaurant_create_menu.RestaurantCreateMenuScreen
import com.example.meintasty.feature.user_feature.detail_restaurant.DetailRestaurantScreen
import com.example.meintasty.feature.user_feature.order_screen.OrderScreen
import com.example.meintasty.feature.user_feature.password_screen.PasswordScreen
import com.example.meintasty.feature.user_feature.payment_screen.PaymentScreen
import com.example.meintasty.feature.user_feature.user_profile_screen.ProfileScreen
import com.example.meintasty.feature.restaurant_feature.restaurant_login_screen.RestaurantLoginScreen
import com.example.meintasty.feature.restaurant_feature.restaurant_menu_list.RestaurantMenuListScreen
import com.example.meintasty.feature.restaurant_feature.restaurant_profile_screen.RestaurantProfileScreen
import com.example.meintasty.feature.user_feature.restaurant_screen.RestaurantScreen
import com.example.meintasty.feature.user_feature.update_screen.UpdateScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph() {
    val navController = rememberNavController()
    SharedTransitionLayout {
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
            composable(Screen.RestaurantScreen.route) {
                RestaurantScreen(
                    animatedVisibilityScope = this,
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
                    animatedVisibilityScope = this,
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
                    categoryName = navBackStack.arguments?.getString("categoryName"),
                    navController
                )
            }
            composable(Screen.BasketScreen.route) {
                BasketScreen(navController)
            }
            composable(Screen.ProfileScreen.route) {
                ProfileScreen(navController = navController)
            }

            composable(
                route = "update_screen?userId={userId}&email={email}&phone={phone}&updateType={updateType}",
                arguments = listOf(
                    navArgument(name = "userId") {
                        type = NavType.IntType
                    },
                    navArgument(name = "phone") {
                        type = NavType.StringType
                    },
                    navArgument(name = "email") {
                        type = NavType.StringType
                    },
                    navArgument(name = "updateType") {
                        type = NavType.StringType
                    }
                )
            ) { navBackStack ->
                UpdateScreen(
                    userId = navBackStack.arguments?.getInt("userId"),
                    email = navBackStack.arguments?.getString("email"),
                    phoneNum = navBackStack.arguments?.getString("phone"),
                    updateType = navBackStack.arguments?.getString("updateType"),
                    navController = navController
                )
            }

            composable(
                route = Screen.PasswordScreen.route + "&userId={userId}",
                arguments = listOf(
                    navArgument(name = "userId") {
                        type = NavType.IntType
                    }
                )
            ) { navBackStack ->
                PasswordScreen(
                    userId = navBackStack.arguments?.getInt("userId"),
                    navController = navController
                )
            }
            composable(Screen.OrderScreen.route) {
                OrderScreen(navController = navController)
            }
            composable(Screen.PaymentScreen.route) {
                PaymentScreen(navController)
            }
            composable(Screen.RestaurantLoginScreen.route){
                RestaurantLoginScreen(navController)
            }
            composable(Screen.RestaurantProfileScreen.route){
                RestaurantProfileScreen(navController)
            }
            composable(
                route = Screen.RestaurantMenuDetailScreen.route,
            ){
                RestaurantMenuListScreen(
                    animatedVisibilityScope = this,
                    navController =navController ,
                )
            }
            composable(
                route = Screen.RestaurantCreateMenuScreen.route
            ){
                RestaurantCreateMenuScreen(
                    navController = navController,
                )
            }
        }
    }
}