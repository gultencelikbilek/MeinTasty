package com.example.meintasty.uicomponent

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.ArcMode
import androidx.compose.animation.core.ExperimentalAnimationSpecApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import com.example.meintasty.R
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import com.example.meintasty.domain.model.favorites_restaurants_model.favorite_restauranst_response.FavoriteRestaurant
import com.example.meintasty.navigation.Screen

@OptIn(ExperimentalAnimationSpecApi::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.FoodCardComponent(
    animatedVisibilityScope: AnimatedVisibilityScope,
    favoriteRestaurant: FavoriteRestaurant?,
    navController: NavController
) {
    var isHovered by remember { mutableStateOf(false) } // isHovered değişkenini tanımlıyoruz

    val elevation by animateFloatAsState(if (isHovered) 12f else 6f) // Hover durumuna göre elevasyon değişimi
    val scale by animateFloatAsState(if (isHovered) 1.05f else 1f) // Hover durumuna göre ölçek değişimi

    val arcBoundsTransform = BoundsTransform { initialBounds, targetBounds ->
        keyframes {
            durationMillis = 3000
            initialBounds at 0 using ArcMode.ArcBelow using FastOutSlowInEasing
            targetBounds at 1000
        }
    }
    Card(
        modifier = Modifier
            .wrapContentWidth()
            .height(300.dp)
            .padding(start = 8.dp, end = 8.dp)
            .scale(scale)
            .shadow(elevation.dp)
            .detectHover { isHovered = it }
            .clickable {
                navController.navigate(Screen.DetailRestaurantScreen.route + "?restaurantId=${favoriteRestaurant?.id}")
            },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(elevation.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            Image(
                painter = painterResource(id = R.drawable.food_one),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
                    .sharedElement(
                        state = rememberSharedContentState(key = "image_${favoriteRestaurant?.id}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = arcBoundsTransform
                    ),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .background(Color.Black.copy(alpha = 0.2f))
                    .padding(8.dp)
            ) {
                Text(
                    text = favoriteRestaurant!!.restaurantName.toString(),
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Color.White
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

// Yardımcı fonksiyon: hover durumunu tespit etmek için
fun Modifier.detectHover(onHover: (Boolean) -> Unit): Modifier = pointerInput(Unit) {
    detectTapGestures(
        onPress = {
            onHover(true) // Hover durumunu true yap
            tryAwaitRelease() // Kullanıcı fareyi bıraktı mı kontrol et
            onHover(false) // Hover durumunu false yap
        }
    )
}
