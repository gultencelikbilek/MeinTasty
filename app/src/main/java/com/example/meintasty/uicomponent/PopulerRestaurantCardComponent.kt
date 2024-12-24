package com.example.meintasty.uicomponent

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.meintasty.R
import com.example.meintasty.domain.model.user_model_.restaurant_model.restaurant_response.Restaurant
import com.example.meintasty.navigation.Screen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.PopulerRestaurantCardComponent(
    animatedVisibilityScope: AnimatedVisibilityScope,
    restaurant: Restaurant?,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .width(250.dp)
            .padding(top = 16.dp, start = 16.dp)
            .height(150.dp)
            .clickable {
               navController.navigate(Screen.DetailRestaurantScreen.route + "?restaurantId=${restaurant?.id}")
            },
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.restaurant_bg),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .background(Color.White.copy(alpha = 0.7f))
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Column {
                    Text(
                        text = restaurant?.restaurantName.toString(),//.restaurantName.toString(),
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    val regex = """(\d{2})(\d{3})(\d{3})(\d{2})(\d{2})""".toRegex()
                    val number = restaurant?.phoneNumber.toString()
                    val output = regex.replace(number, "+$1 $2 $3-$4-$5")
                    Text(
                        text = output,
                        style = TextStyle(
                            color = Color.Gray,
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            fontWeight = FontWeight.Normal,
                        )
                    )
                }
            }
        }
    }
}