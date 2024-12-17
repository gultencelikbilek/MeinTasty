package com.example.meintasty.feature.common_screen_feature.canton_screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.meintasty.domain.model.canton_model.request_model.CantonRequestModel
import com.example.meintasty.uicomponent.BeatMeCardComponent

@Composable
fun CantonScreen(
    navController: NavController,
    cantonViewModel: CantonViewModel = hiltViewModel()
) {

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


