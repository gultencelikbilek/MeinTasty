package com.example.meintasty.uicomponent

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.meintasty.R
import com.example.meintasty.domain.model.restaurant_model.restaurant_response.Restaurant
import com.example.meintasty.navigation.Screen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.PopulerRestaurantCardComponent(
    animatedVisibilityScope: AnimatedVisibilityScope,
    restaurant: Restaurant,
    navController: NavController
) {

    Card(
        modifier = Modifier
            .width(250.dp)
            .padding(top = 16.dp, start = 16.dp)
            .height(150.dp)
            .clickable {
                navController.navigate(Screen.DetailRestaurantScreen.route + "?restaurantId=${restaurant.id}")
            },
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Resim bileşeni
            Image(
                painter = painterResource(id = R.drawable.restaurant_bg),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )

            // Sağ üst köşeye tıklanabilir "like" ikonu
            IconButton(
                onClick = { /* Like işlemini burada yapabilirsiniz */ },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .size(24.dp)
                    .zIndex(1f)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.heart),
                    contentDescription = "Like",
                    tint = Color.Black
                )
            }

            // Yazıları resmin üstünde göstermek için yarı şeffaf bir arka plan kutusu
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .background(Color.White.copy(alpha = 0.7f))
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Column {
                    Text(
                        text = restaurant.restaurantName.toString(),
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = restaurant.phoneNumber.toString(),
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

@Preview
@Composable
fun Prew(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Box(
            modifier = Modifier
                .background(Color.Black)
                .align(Alignment.CenterHorizontally)
        ) {
            IconButton(
                onClick = { /* Like işlemini burada yapabilirsiniz */ },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(24.dp)
                    .padding(8.dp)
                    .zIndex(1f) // IconButton'un diğer bileşenlerin üstünde yer almasını sağlar
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.heart), // Like ikonu, tercihe göre değiştirilebilir
                    contentDescription = "Like",
                    tint = Color.Red
                )
            }
            Image(
                painter = painterResource(id = R.drawable.food_one),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.wrapContentSize()

            )

        }
    }
}
