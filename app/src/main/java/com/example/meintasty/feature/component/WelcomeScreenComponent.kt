package com.example.meintasty.feature.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.meintasty.R

@Composable
fun SettingComponent(onClick: () -> Unit, icon: Painter) {

    Box(
        modifier = Modifier
            .padding(16.dp)
            .size(48.dp)
            .background(Color(0xffd63384), RoundedCornerShape(8.dp))
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color(0xffd63384), RoundedCornerShape(12.dp))
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = icon,
                contentDescription = stringResource(
                    id = R.string.back
                ),
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
@Composable
fun BeatMeCardComponent() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.restaurant_bg),
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .background(Color(0xffdc3545))
                .fillMaxSize()
        ){
            // Card görselin üstünde yer alacak
            Card(
                modifier = Modifier
                    .align(Alignment.BottomCenter)  // Card'ı Box'ın altına hizalar
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(100.dp),
                shape = RoundedCornerShape(16.dp),
                elevation =CardDefaults.cardElevation(8.dp)
            ) {
                // Card'ın içeriği
                Text(
                    text = "Card on Top of Image",
                    modifier = Modifier
                        .padding(16.dp)
                )
            }
        }
    }
}



