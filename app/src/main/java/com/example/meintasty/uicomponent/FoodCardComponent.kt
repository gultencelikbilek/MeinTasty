package com.example.meintasty.uicomponent

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
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
import com.example.meintasty.domain.model.Food
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.input.pointer.pointerInput

@Composable
fun FoodCardComponent(food: Food) {
    var isHovered by remember { mutableStateOf(false) } // isHovered değişkenini tanımlıyoruz

    val elevation by animateFloatAsState(if (isHovered) 12f else 6f) // Hover durumuna göre elevasyon değişimi
    val scale by animateFloatAsState(if (isHovered) 1.05f else 1f) // Hover durumuna göre ölçek değişimi

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(start = 8.dp, end = 8.dp)
            .scale(scale)
            .shadow(elevation.dp)
            .detectHover { isHovered = it }, // Burada detectHover kullanılıyor
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
                painter = painterResource(id = food.img),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
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
