package com.example.meintasty.uicomponent

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.meintasty.R
import com.example.meintasty.data.Constants
import com.example.meintasty.domain.model.UserLocationModel
import com.example.meintasty.feature.canton_screen.CantonViewModel
import com.example.meintasty.navigation.Screen

@Composable
fun BeatMeCardComponent(cantonViewModel: CantonViewModel, navController: NavController) {


    var cantonSelect by remember {
        mutableStateOf("")
    }
    var citiesSelect by remember {
        mutableStateOf("")
    }
    var selectedCityCode by remember {
        mutableStateOf("")
    }
    val cantonList by cantonViewModel.canton.collectAsState()
    val citiesList by cantonViewModel.cities.collectAsState()
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE)

    LaunchedEffect(cantonSelect) {
        val selectedCanton = cantonViewModel.canton.value.find { it.cantonName == cantonSelect }
        selectedCanton?.let {
            cantonViewModel.updateCities(it) // Seçilen Canton nesnesini ViewModel'e gönderiyoruz
        }
    }

    LaunchedEffect(citiesSelect) {
        val selectedCity = citiesList.find { it.cityName == citiesSelect }
        selectedCity.let {
            selectedCityCode = it?.cityCode.toString()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)

    ) {
        Image(
            painter = painterResource(id = R.drawable.restaurant_bg),
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Card(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
                .fillMaxWidth()
                .height(250.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.Bottom
            ) {

                CantonTextFieldComponent(
                    cantonList,
                    cantonSelect,
                    onCantonChange = { newCanton ->
                        cantonSelect = newCanton
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                CitiesTextFieldComponent(
                    citiesList,
                    citiesSelect,
                    onCitiesChange = {
                        citiesSelect = it
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                SearchButton(onClick = {
                    if (cantonSelect.isNullOrEmpty().not() && citiesSelect.isNullOrEmpty().not()) {
                        val userLocationModel = UserLocationModel(0, cantonSelect, citiesSelect)
                        cantonViewModel.saveCantonCities(userLocationModel)
                        Log.d("userLocationModelScreen:", "$userLocationModel")

                        val editor = sharedPreferences.edit()
                        editor.putString("city_code", selectedCityCode)
                        editor.apply() // Değişiklikleri kaydet

                        navController.navigate(Screen.RestaurantScreen.route)
                    } else {
                        Log.d("Navigation:", "Missing Information")
                    }
                }
                )
            }
        }
    }
}


