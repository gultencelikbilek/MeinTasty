package com.example.meintasty.feature.restaurant_feature.restaurant_menu_list

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.meintasty.R
import com.example.meintasty.data.Constants
import com.example.meintasty.domain.model.user_model_.restaurant_detail.restaurant_detail_request.DetailRestaurantRequest
import com.example.meintasty.feature.restaurant_feature.restaurant_profile_screen.RestaurantProfileViewModel
import com.example.meintasty.navigation.Screen
import com.example.meintasty.uicomponent.BackIcon
import com.example.meintasty.uicomponent.HeaderComponent
import com.example.meintasty.uicomponent.RestaurantMenuListCardComponent

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SharedTransitionScope.RestaurantMenuListScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    navController: NavController,
    restaurantMenuListViewModel: RestaurantMenuListViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val sharedPreferences =
        context.getSharedPreferences(Constants.SHARED_RESTAURANT_ID, Context.MODE_PRIVATE)
    val restaurantId = sharedPreferences.getInt(Constants.SHARED_RESTAURANT_ID, 0)
    val detailRestState = restaurantMenuListViewModel.restaurantMenuListState.collectAsState().value
    val selectedCategoryId = remember { mutableStateOf<Int?>(null) }
    val gridState = rememberLazyGridState()

    LaunchedEffect(restaurantId) {
        restaurantMenuListViewModel.getRestaurantDatabase()
        restaurantId?.let {
            restaurantMenuListViewModel.getDetailRestaurant(DetailRestaurantRequest(it))
            Log.d("restaurantId","$restaurantId")
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BackIcon { navController.navigateUp() }
                        HeaderComponent(text = stringResource(id = R.string.retaurant_menu_list))
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Screen.RestaurantCreateMenuScreen.route)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.plus),
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
        content = { paddingValues ->
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)) {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom
                ) {
                    detailRestState.data?.menuList?.let { menuList ->
                        Log.d("MenuListDebug", "MenuList: ${detailRestState.data?.menuList}")
                        val distinctCategories =
                            menuList.filterNotNull().distinctBy { it.categoryId }

                        items(distinctCategories) { category ->
                            val isSelected =
                                selectedCategoryId.value == category.categoryId // Şu anki kategori seçili mi?
                            Box(
                                modifier = Modifier
                                    .width(80.dp)
                                    .height(40.dp)
                                    .padding(horizontal = 8.dp, vertical = 6.dp)
                                    .clip(RoundedCornerShape(25.dp)) // Dış şekli kliple
                                    .background(
                                        color = if (isSelected) colorResource(id = R.color.mein_tasty_color) else colorResource(
                                            id = R.color.white
                                        )
                                    )
                                    .clickable {
                                        selectedCategoryId.value =
                                            category.categoryId // Seçili kategoriyi güncelle
                                    }
                            ) {
                                Text(
                                    text = category.categoryName.orEmpty(),
                                    style = TextStyle(
                                        fontSize = MaterialTheme.typography.titleSmall.fontSize,
                                        color = if (isSelected) colorResource(id = R.color.white) else colorResource(
                                            id = R.color.mein_tasty_color
                                        )
                                    ),
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        }
                    }
                }

                detailRestState.data?.menuList.let { menuList ->
                    val filteredMenuList = if (selectedCategoryId.value == null) {
                        menuList
                    } else {
                        menuList?.filter { it?.categoryId == selectedCategoryId.value }
                    }

                    LaunchedEffect(filteredMenuList) {
                        gridState.scrollToItem(0) // Liste her değiştiğinde başa kaydır
                    }

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        state = gridState,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .background(Color.White)
                    ) {
                        filteredMenuList?.let {
                            items(it) {
                                RestaurantMenuListCardComponent(
                                    animatedVisibilityScope = animatedVisibilityScope,
                                    menu = it,
                                    navController = navController
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}
