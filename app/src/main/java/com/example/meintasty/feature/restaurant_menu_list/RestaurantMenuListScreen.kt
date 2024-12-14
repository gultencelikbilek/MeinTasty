package com.example.meintasty.feature.restaurant_menu_list

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.meintasty.domain.model.restaurant_detail.restaurant_detail_request.DetailRestaurantRequest
import com.example.meintasty.feature.restaurant_profile_screen.RestaurantProfileViewModel
import com.example.meintasty.uicomponent.MenuListCardComponent

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.RestaurantMenuListScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    navController: NavController,
    restaurantViewModel: RestaurantProfileViewModel = hiltViewModel()
) {

    val detailMenuRestState = restaurantViewModel.detailRestProfState.collectAsState()
    val restaurantIdState = restaurantViewModel.restaurantDatabaseState.collectAsState()
    val restaurantId = restaurantIdState.value.data?.restaurantId
    LaunchedEffect(restaurantId) {
        restaurantViewModel.getRestaurantDatabase()
        restaurantId?.let {
            restaurantViewModel.getDetailRestaurant(DetailRestaurantRequest(it))
        }
    }

    Scaffold(
        content = {paddingValues ->
            LazyColumn(modifier = Modifier.padding(paddingValues)) {
                detailMenuRestState.value.data?.menuList.let {menuList->
                    items(menuList){menu->

                        MenuListCardComponent(
                            animatedVisibilityScope = animatedVisibilityScope,
                            menu = menu,
                            detailRestaurantViewModel =restaurantViewModel
                        )
                    }
                }
            }
        }
    )


}