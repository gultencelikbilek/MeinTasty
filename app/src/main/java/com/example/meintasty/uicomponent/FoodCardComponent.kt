package com.example.meintasty.uicomponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.meintasty.domain.model.Food

@Composable
fun FoodCardComponent(food: Food) {
    Card(
        modifier = Modifier
            .width(200.dp)
            .height(300.dp)
            .background(Color.White)
            .padding(start = 16.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
            Box(
                modifier = Modifier
                    .width(200.dp)
                    .height(300.dp)
            ) {
                Image(
                    painter = painterResource(id = food.img),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop // Resmin alanı doldurması için
                )
        }
    }
}


@Composable
fun FoodCardPrew(food: Food) {
    FoodCardComponent(food)
}