package com.example.meintasty.feature.canton_screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.meintasty.domain.model.canton_model.CantonRequestModel
import com.example.meintasty.domain.model.foodList
import com.example.meintasty.uicomponent.BeatMeCardComponent
import com.example.meintasty.uicomponent.FoodCardComponent

@Composable
fun CantonScreen(
    navController: NavController,
    cantonViewModel: CantonViewModel = hiltViewModel()
) {
    val requestModel = CantonRequestModel()

    LaunchedEffect(Unit) {
        cantonViewModel.getCanton(requestModel)  // Modeli burada ViewModel'e geçiyoruz
        Log.d("cantonscreen",cantonViewModel.canton.toString())
    }

    Scaffold(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        content = { paddingValues ->
            Column(
                modifier = Modifier.padding(paddingValues),
                verticalArrangement = Arrangement.Bottom
            ) {
                BeatMeCardComponent(cantonViewModel,navController)
                Spacer(modifier = Modifier.height(10.dp))
                LazyRow(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    foodList.let {listFood ->
                        items(listFood) {
                            FoodCardComponent(it)
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }
                }
              //  Spacer(modifier = Modifier.width(50.dp))
              //  MenuButtonComponent(onClick = {})
              //  Spacer(modifier = Modifier.height(10.dp))
              //  HotTodayComponent()
              //  BreakfastCardComponent()
            }
        }
    )
}

@Preview
@Composable
fun CantonScreenPrew() {
    val navController = rememberNavController()
    CantonScreen(navController = navController)

}


