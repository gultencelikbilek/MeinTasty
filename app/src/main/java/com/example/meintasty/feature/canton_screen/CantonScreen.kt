package com.example.meintasty.feature.canton_screen

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.meintasty.data.Constants
import com.example.meintasty.domain.model.canton_model.request_model.CantonRequestModel
import com.example.meintasty.domain.model.foodList
import com.example.meintasty.navigation.Screen
import com.example.meintasty.uicomponent.BeatMeCardComponent
import com.example.meintasty.uicomponent.FoodCardComponent

@Composable
fun CantonScreen(
    navController: NavController,
    cantonViewModel: CantonViewModel = hiltViewModel()
) {
    /*val splashShowState = cantonViewModel.splashShow.collectAsState().value
    val splashRestState = cantonViewModel.splashRestShow.collectAsState().value
    LaunchedEffect(splashShowState, splashRestState) {
        when {
            splashShowState.data?.isUser == true -> {
                navController.navigate(Screen.RestaurantScreen.route)
            }

            splashRestState.data?.isRestaurant == true -> {
                navController.navigate(Screen.RestaurantProfileScreen.route)
            }
        }
    }*/


    val requestModel = CantonRequestModel()
    LaunchedEffect(Unit) {
        cantonViewModel.getCanton(requestModel)
        Log.d("cantonscreen", cantonViewModel.canton.toString())
    }
    Surface(
        modifier = Modifier.verticalScroll(rememberScrollState()),

        ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Bottom
        ) {
            BeatMeCardComponent(cantonViewModel, navController)

        }
    }
}

@Preview
@Composable
fun CantonScreenPrew() {
    val navController = rememberNavController()
    CantonScreen(navController = navController)

}


