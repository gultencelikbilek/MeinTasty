package com.example.meintasty.feature.detail_restaurant

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.meintasty.R
import com.example.meintasty.domain.model.restaurant_detail.DetailRestaurantRequest
import com.example.meintasty.feature.component.BackIcon
import com.example.meintasty.feature.component.MenuListCardComponent
import com.example.meintasty.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailRestaurantScreen(
    restaurantId: Int?,
    navController: NavController,
    detailRestaurantViewModel: DetailRestaurantViewModel = hiltViewModel()
) {

    val detailRestState = detailRestaurantViewModel.detailRestState.collectAsState()

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
                        detailRestState.value.data?.restaurantName?.let { it1 ->
                            Text(
                                text = it1,
                                modifier=Modifier.padding(top = 6.dp) ,
                                color = Color.Black,
                                fontFamily = customFontFamily
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 50.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.food_one),
                        contentDescription = "",
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(16.dp)
                        .background(Color.White)
                ) {

                    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                        detailRestState.value.data?.let {
                            items(it.menuList!!) { menu ->
                                MenuListCardComponent(menu)
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun DetailRestaurantPrew(restaurantId: Int?) {
    val navController = rememberNavController()
    DetailRestaurantScreen(restaurantId = restaurantId, navController = navController)

}