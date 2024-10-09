package com.example.meintasty.feature.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.meintasty.R
import com.example.meintasty.domain.model.Food
import com.example.meintasty.domain.model.Restaurant
import com.example.meintasty.navigation.Screen

@Composable
fun PopulerRestaurantCardComponent(restaurant: Restaurant,navController: NavController) {

    Card(
        modifier = Modifier
            .width(250.dp)
            .padding(top = 16.dp)
            .padding(start = 16.dp)
            .height(150.dp)
            .background(Color.White)
            .clickable {
                       navController.navigate(Screen.DetailRestaurantScreen.route+"?restaurantId=${restaurant.id}")
            },
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Color.White).fillMaxWidth().fillMaxHeight()
        ) {
            Box(
                modifier = Modifier.background(Color.LightGray).align(Alignment.CenterHorizontally)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.food_one),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = restaurant.restaurantName.toString(),
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                )
              //  Row(
              //      modifier = Modifier.padding(top = 16.dp)
              //  ) {
                    Text(
                        text = restaurant.phoneNumber.toString(),
                        style = TextStyle(
                            color = Color.Gray,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            fontWeight = FontWeight.Normal,
                            textDecoration = TextDecoration.LineThrough
                        )
                    )
                    //Spacer(modifier = Modifier.width(10.dp))
                  // Text(
                  //     text = restaurant.phoneNumber,
                  //     style = TextStyle(
                  //         color = Color.DarkGray,
                  //         fontSize = MaterialTheme.typography.titleLarge.fontSize,
                  //         fontWeight = FontWeight.Normal,
                  //     )
                  // )
               // }
            }
        }
    }
}