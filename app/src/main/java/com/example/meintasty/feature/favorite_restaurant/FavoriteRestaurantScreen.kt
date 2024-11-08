package com.example.meintasty.feature.favorite_restaurant

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.meintasty.R
import com.example.meintasty.domain.model.favorites_restaurants_model.favorite_restauranst_request.FavoritesRestaurantRequest
import com.example.meintasty.domain.model.favorites_restaurants_model.favorite_restauranst_response.FavoriteRestaurant
import com.example.meintasty.navigation.Screen
import com.example.meintasty.uicomponent.BackIcon
import com.example.meintasty.uicomponent.FavoriteRestaurantCardComponent
import com.example.meintasty.uicomponent.HeaderComponent
import com.example.meintasty.uicomponent.ScreenImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteRestaurantScreen(
    navController: NavController,
    favoriteRestaurantViewModel: FavoriteRestaurantViewModel = hiltViewModel()
) {

    val favoriteRestaurantState =
        favoriteRestaurantViewModel.favoriteRestaurantState.collectAsState()
    LaunchedEffect(Unit) {
        val favoriteRestaurantRequest = FavoritesRestaurantRequest()
        favoriteRestaurantViewModel.getFavoriteRestaurant(favoriteRestaurantRequest)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        BackIcon(onClick = {
                            navController.navigate(Screen.ProfileScreen.route)
                        })
                        Spacer(modifier = Modifier.width(6.dp))
                        HeaderComponent(text = stringResource(id = R.string.favorite_restaurant))

                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.mein_tasty_color),
                )
            )
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier.padding(paddingValues)
            ) {
                favoriteRestaurantState.value?.let {
                    val nonNullRestaurants = it.data?.filterNotNull() // Null olmayan öğeleri al
                    items(nonNullRestaurants ?: emptyList()) { restaurant ->
                        FavoriteRestaurantCardComponent(restaurant)
                    }
                } ?: run {
                    Log.d("error", "error")

                }
            }
        }
    )
}