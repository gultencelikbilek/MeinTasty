package com.example.meintasty.feature.restaurant_screen

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.LocalContext
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
import com.example.meintasty.domain.model.category_model.CategoryRequest
import com.example.meintasty.domain.model.restaurant_model.RestaurantRequest
import com.example.meintasty.domain.model.foodList
import com.example.meintasty.uicomponent.CategoryCardComponent
import com.example.meintasty.uicomponent.FoodCardComponent
import com.example.meintasty.uicomponent.NearbyRestaurantCardComponent
import com.example.meintasty.uicomponent.PopulerRestaurantCardComponent
import com.example.meintasty.uicomponent.SearchComponent
import com.example.meintasty.uicomponent.SearchHeaderComponent
import com.example.meintasty.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantScreen(
    navController: NavController,
    restaurantViewModel: RestaurantViewModel = hiltViewModel()
) {
    var query by remember {
        mutableStateOf("")
    }
    val customFontFamily = FontFamily(
        Font(resId = R.font.poppins_extralight, weight = FontWeight.Normal)
    )
    val context = LocalContext.current

    val sharedPreferences = context.getSharedPreferences("city_code", Context.MODE_PRIVATE)

    val restaurantState = restaurantViewModel.restaurantState.collectAsState()
    val locaitonState = restaurantViewModel.locationState.collectAsState()

    val categoryState = restaurantViewModel.categoryState.collectAsState()
    val categoryRequest = CategoryRequest()

    val cityCode = sharedPreferences.getString("city_code", null)
    Log.d("city_code", "$cityCode")
    cityCode?.let {
        val restaurantRequest = RestaurantRequest(it.toInt())

        LaunchedEffect(Unit) {
            restaurantViewModel.getRestaurant(restaurantRequest)
            Log.d("screen", "searchscreen")
        }
    }
    LaunchedEffect(Unit) {
        restaurantViewModel.getLocationInfo()
    }

    LaunchedEffect(Unit) {
        restaurantViewModel.getCategoryList(categoryRequest)
        Log.d("screen", "categoryList")
    }

    Log.d("cityCode:", cityCode.toString())
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
                        locaitonState.value.data?.let { locationInfo ->
                            Text(
                                text = "${locationInfo.cantonName}/${locationInfo.cityName}",
                                style = TextStyle(
                                    color = Color.White,
                                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                    fontFamily = customFontFamily
                                )
                            )
                        }
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.CartScreen.route)
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.cart),
                            contentDescription = "",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
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
                        onQueryChange = {
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
                ) {
                    SearchHeaderComponent(text = stringResource(id = R.string.categories))
                    LazyRow(
                        modifier = Modifier
                            .padding(top = 30.dp)
                            .fillMaxWidth()
                    ) {
                        categoryState.value.data?.let { categoryList ->
                            items(categoryList) { category ->
                                CategoryCardComponent(
                                    navController,
                                    category
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    SearchHeaderComponent(text = stringResource(id = R.string.populer_restaurants))
                    LazyRow(
                        modifier = Modifier
                            .padding(top = 30.dp)
                            .fillMaxWidth()
                    ) {
                        restaurantState.value.data?.let { restaurantList ->
                            val repeatedList =
                                List(10) { restaurantList }.flatten() // 10 kere categoryDetailList'i tekrarlıyoruz ve düz listeye dönüştürüyoruz
                            items(repeatedList) { restaurant ->
                                Log.d("restaurantList:", restaurantList.toString())
                                PopulerRestaurantCardComponent(
                                    restaurant = restaurant,
                                    navController
                                )
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