package com.example.meintasty.feature.detail_restaurant

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.meintasty.R
import com.example.meintasty.domain.model.restaurant_detail.restaurant_detail_request.DetailRestaurantRequest
import com.example.meintasty.uicomponent.BackIcon
import com.example.meintasty.uicomponent.MenuListCardComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailRestaurantScreen(
    restaurantId: Int?,
    navController: NavController,
    detailRestaurantViewModel: DetailRestaurantViewModel = hiltViewModel()
) {

    val detailRestState = detailRestaurantViewModel.detailRestState.collectAsState().value

    val detailRestaurantRequest = DetailRestaurantRequest(restaurantId!!.toInt())
    val customFontFamily = FontFamily(
        Font(resId = R.font.poppins_bold, weight = FontWeight.Normal)
    )
    LaunchedEffect(Unit) {
        detailRestaurantViewModel.getDetailRestaurant(detailRestaurantRequest)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        BackIcon {
                            navController.navigateUp()
                        }
                        detailRestState.data?.restaurantName?.let { it1 ->
                            Text(
                                text = it1,
                                modifier = Modifier
                                    .padding(start = 4.dp)
                                    .padding(top = 6.dp),
                                color = Color.White,
                                fontFamily = customFontFamily
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.mein_tasty_color)
                )
            )
        },
        content = { paddingValues ->
            if (detailRestState.isLoading == true) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = colorResource(id = R.color.mein_tasty_color)
                    )
                }
            } else if (detailRestState.isSuccess == true) {
                Log.d("succes:","${detailRestState.data}")
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.food_one),
                            contentDescription = "",
                            modifier = Modifier.size(180.dp),
                            contentScale = ContentScale.Crop
                        )
                    }

                    detailRestState.data?.let { list ->
                        val repeatedMenuList = List(10) { list.menuList!! }.flatten()
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                                .background(Color.White)
                        ) {
                            items(repeatedMenuList) { menu ->
                                MenuListCardComponent(menu)
                            }
                        }
                    }
                }
            }else{
                Log.d("detailRestaurant:error","${detailRestState.isError}")
            }
        }
    )

}


    @Composable
    fun DetailRestaurantPrew(restaurantId: Int?) {
        val navController = rememberNavController()
        DetailRestaurantScreen(restaurantId = restaurantId, navController = navController)

    }