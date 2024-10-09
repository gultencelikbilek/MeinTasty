package com.example.meintasty.feature.detail_restaurant

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.meintasty.R
import com.example.meintasty.domain.model.DetailRestaurantRequest
import com.example.meintasty.feature.component.BackIcon
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

    LaunchedEffect(Unit) {
        detailRestaurantViewModel.getDetailRestaurant(detailRestaurantRequest)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    BackIcon {
                        navController.navigate(Screen.RestaurantScreen.route)
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
                        modifier = Modifier
                            .size(250.dp),
                        contentScale = ContentScale.Crop
                    )
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(600.dp)
                        .background(Color.White),
                    elevation = CardDefaults.cardElevation(2.dp),
                    border = BorderStroke(2.dp, Color.LightGray),
                    shape = RoundedCornerShape(topEnd = 25.dp, topStart = 25.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(16.dp)
                    ) {
                        detailRestState.value.data?.let {
                            Text(
                                text = it.restaurantName,
                                color = Color.Black
                            )
                        }

                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun DetailRestaurantPrew(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    // DetailRestaurantScreen(navController = navController)

}