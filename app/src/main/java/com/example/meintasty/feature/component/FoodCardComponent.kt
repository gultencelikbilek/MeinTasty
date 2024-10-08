package com.example.meintasty.feature.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.meintasty.R
import com.example.meintasty.domain.model.Food

@Composable
fun FoodCardComponent(food: Food) {


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .background(Color.White),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(40.dp)
    ) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .width(150.dp)
                    .height(200.dp)
            ) {
                Image(
                    painter = painterResource(id = food.img),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop // Resmin alanı doldurması için
                )
            }
            Column(
                modifier = Modifier.padding(top = 24.dp, start = 20.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically // Dikey h
                ) {
                    Text(
                        text = food.name,
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.love),
                        contentDescription = "",
                        tint = Color.Red,
                        modifier = Modifier.size(16.dp)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = stringResource(id = R.string.lorem_ipsum),
                    style = TextStyle(
                        color = Color.LightGray,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        fontWeight = FontWeight.Normal
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    Text(
                        text = stringResource(id = R.string.dollars),
                        style = TextStyle(
                            color = Color.Gray,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            fontWeight = FontWeight.Normal,
                            textDecoration = TextDecoration.LineThrough // Üstü çizili
                        )
                    )
                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = stringResource(id = R.string.price),
                        style = TextStyle(
                            color = Color.DarkGray,
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                            fontWeight = FontWeight.Normal,
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun FoodCardPrew(modifier: Modifier = Modifier) {
   // FoodCardComponent(it)
}