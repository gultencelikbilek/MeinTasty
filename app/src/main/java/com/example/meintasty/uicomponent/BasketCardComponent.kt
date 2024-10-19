package com.example.meintasty.uicomponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.meintasty.R
import com.example.meintasty.domain.model.get_basket_model.get_basket_response.Basket

@Composable
fun BasketCardComponent(basket: Basket?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(1.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(modifier = Modifier.wrapContentSize()){
                Image(
                    painter = painterResource(id = R.drawable.food_one),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier.padding(start = 2.dp, top = 8.dp)
            ) {
                Text(
                    text = basket?.menuName.toString(),
                    style = TextStyle(
                        color = Color.DarkGray,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = basket?.restaurantName.toString(),
                    style = TextStyle(
                        color = Color.DarkGray,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize
                    )
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = basket?.price.toString(),
                style = TextStyle(
                    color = Color.DarkGray,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize
                )
            )
        }
    }
}

@Preview
@Composable
fun BasketPrew() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(1.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(modifier = Modifier.wrapContentSize()){
                Image(
                    painter = painterResource(id = R.drawable.food_one),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}