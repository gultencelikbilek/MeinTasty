package com.example.meintasty.feature.restaurant_screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.meintasty.R
import com.example.meintasty.domain.model.restaurant_model.RestaurantRequest
import com.example.meintasty.domain.model.foodList
import com.example.meintasty.feature.component.FoodCardComponent
import com.example.meintasty.feature.component.NearbyRestaurantCardComponent
import com.example.meintasty.feature.component.PopulerRestaurantCardComponent
import com.example.meintasty.feature.component.SearchComponent
import com.example.meintasty.feature.component.SearchHeaderComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantScreen(
    cityCode : String?,
    navController: NavController,
    restaurantViewModel: RestaurantViewModel = hiltViewModel()
) {
    var query by remember {
        mutableStateOf("")
    }
    val customFontFamily = FontFamily(
        Font(resId = R.font.poppins_bold, weight = FontWeight.Bold)
    )
    val restaurantState = restaurantViewModel.restaurantState.collectAsState()
    val locaitonState = restaurantViewModel.locationState.collectAsState()

    val restaurantRequest = RestaurantRequest(cityCode!!.toInt())
    LaunchedEffect(Unit) {
        restaurantViewModel.getRestaurant(restaurantRequest)
        restaurantViewModel.getLocationInfo()
        Log.d("screen","searchscreen")
    }



    Log.d("cityCode:",cityCode.toString())
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = stringResource(id = R.string.mein_tasty),
                            style = TextStyle(
                                color = Color.White,
                                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                                fontFamily = customFontFamily
                            )
                        )
                        locaitonState.value.data?.let {locationInfo ->
                            Text(
                                text = "${locationInfo.cantonName}/${locationInfo.cityName}",
                               // modifier = Modifier.padding(bottom = ),
                                style = TextStyle(
                                    color = Color.White,
                                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                    fontFamily = customFontFamily
                                )
                            )
                        }

                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.mein_tasty_color)
                )
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding)
                    .background(colorResource(id = R.color.white))
                    .wrapContentHeight()
                    .verticalScroll(rememberScrollState())
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colorResource(id = R.color.mein_tasty_color))
                        .height(70.dp)
                ) {
                   SearchComponent(
                       query = query,
                       onQueryChange ={
                           query = it
                       }
                   )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(top = 6.dp)
                ) {
                   SearchHeaderComponent(text = stringResource(id = R.string.compains))
                    LazyRow(
                        modifier = Modifier
                            .padding(top = 30.dp)
                            .background(Color.White)
                    ) {
                        foodList.let { listFood ->
                            items(listFood) {
                                FoodCardComponent(food = it)
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                   SearchHeaderComponent(text = stringResource(id = R.string.populer_restaurants))
                    LazyRow(
                        modifier = Modifier
                            .padding(top = 30.dp)
                            .fillMaxWidth()
                    ) {
                        restaurantState.value.data?.let {restaurantList ->
                            items(restaurantList) {restaurant ->
                                Log.d("restaurantList:",restaurantList.toString())
                                PopulerRestaurantCardComponent(restaurant = restaurant,navController)
                                Spacer(modifier = Modifier.width(16.dp))
                            }
                        }

                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                SearchHeaderComponent(text = stringResource(id = R.string.restaurant_nearby))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)

                ) {
                    Image(
                        painter = painterResource(id = R.drawable.google_maps),
                        contentDescription = "Background Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth()
                    )
                    LazyRow(
                        modifier = Modifier
                            .padding(top = 30.dp)
                            .fillMaxWidth()
                    ) {
                        foodList.let { listFood ->
                            items(listFood) {
                                NearbyRestaurantCardComponent(food = it)
                            }
                        }
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun SearchScreenPrew() {
   // SearchScreen()
}