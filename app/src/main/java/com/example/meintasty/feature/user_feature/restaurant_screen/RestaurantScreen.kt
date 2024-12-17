package com.example.meintasty.feature.user_feature.restaurant_screen

import android.content.Context
import android.util.Log
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.meintasty.R
import com.example.meintasty.data.Constants
import com.example.meintasty.domain.model.favorites_restaurants_model.favorite_restauranst_request.FavoritesRestaurantRequest
import com.example.meintasty.domain.model.foodList
import com.example.meintasty.domain.model.restaurant_model.restaurant_request.RestaurantRequest
import com.example.meintasty.domain.model.user_models.category_model.category_request.CategoryRequest
import com.example.meintasty.uicomponent.CategoryCardComponent
import com.example.meintasty.uicomponent.FoodCardComponent
import com.example.meintasty.uicomponent.NearbyRestaurantCardComponent
import com.example.meintasty.uicomponent.PopulerRestaurantCardComponent
import com.example.meintasty.uicomponent.SearchComponent
import com.example.meintasty.uicomponent.SearchHeaderComponent
import com.example.meintasty.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.RestaurantScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
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
    val isLoading = remember { mutableStateOf(false) }
    val favoriteRestaurantState = restaurantViewModel.favoriteRestaurantState.collectAsState()

    val sharedPreferences =
        context.getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE)

    val restaurantState = restaurantViewModel.restaurantState.collectAsState()
    val locaitonState = restaurantViewModel.locationState.collectAsState()

    val categoryState = restaurantViewModel.categoryState.collectAsState()
    val categoryRequest = CategoryRequest()
    val scrollState = rememberLazyGridState()
    val cityCode = sharedPreferences.getString(Constants.SHARED_PREF, null)?.toIntOrNull()

    LaunchedEffect(Unit) {
        val restaurantRequest =
            RestaurantRequest(categoryIdList = listOf(), cityCode = cityCode, pageNumber = 1)
        restaurantViewModel.getRestaurant(restaurantRequest)
        restaurantViewModel.getFavoriteRestaurant(FavoritesRestaurantRequest())
    }

    val fetchNextPage = remember {
        derivedStateOf {
            val totalItems =
                scrollState.layoutInfo.totalItemsCount //listenin toplam öğe sayısını tutar
            val lastDisplayedIndex =
                scrollState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            lastDisplayedIndex >= totalItems - 11

        }
    }
    if (cityCode != null) {
        val totalPage = restaurantState.value.restaurantListInfo?.totalPages ?: 1
        val nextPage = restaurantState.value.restaurantListInfo?.nextPage ?: 1
        if (nextPage <= totalPage) {
            val restaurantRequest = RestaurantRequest(
                categoryIdList = listOf(),
                cityCode = cityCode,
                pageNumber = nextPage
            )
            Log.e("nextPage", "$nextPage")
            if (fetchNextPage.value && !isLoading.value) {
                isLoading.value = true
                LaunchedEffect(fetchNextPage.value) {
                    restaurantViewModel.getRestaurant(restaurantRequest)
                    isLoading.value = false
                }
            }
        }
    } else {
        Log.e("city_code", "City code is null or invalid!")
    }

    LaunchedEffect(Unit) {
        restaurantViewModel.getLocationInfo()
        restaurantViewModel.getCategoryList(categoryRequest)
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
                            navController.navigate(Screen.ProfileScreen.route)
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.user),
                            contentDescription = "",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(2.dp))
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.BasketScreen.route)
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
            if (restaurantState.value.isLoading == true) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = colorResource(id = R.color.mein_tasty_color)
                    )
                }
            } else {
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
                        SearchHeaderComponent(text = stringResource(id = R.string.favorite_restaurant))

                        val favoriteRestaurantList = favoriteRestaurantState.value.data.let {
                            it?.filterNotNull() ?: emptyList()
                        }
                        Log.d("favoriteRestaurantList:", "${favoriteRestaurantList}")
                        val pagerState = rememberPagerState {
                            favoriteRestaurantList.size
                        }

                        HorizontalPager(
                            state = pagerState,
                            contentPadding = PaddingValues(end = 40.dp), // Sayfalar arası boşluk
                            pageSpacing = (-10).dp, // Kartların birbirine yaklaşması için negatif boşluk
                            modifier = Modifier
                                .padding(top = 48.dp)
                        ) { page ->
                            FoodCardComponent(
                                animatedVisibilityScope,
                                favoriteRestaurant = favoriteRestaurantList[page],
                                navController
                            )
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
                        LazyHorizontalGrid(
                            state = scrollState,
                            rows = GridCells.Fixed(1),
                            modifier = Modifier
                                .padding(top = 30.dp)
                                .fillMaxWidth()
                                .height(200.dp)
                        ) {
                            restaurantState.value.data?.let { restaurantList ->
                                items(restaurantList) { restaurant ->
                                    Log.d("restaurantList:", restaurantList.toString())
                                    PopulerRestaurantCardComponent(
                                        animatedVisibilityScope,
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
        }
    )
}

