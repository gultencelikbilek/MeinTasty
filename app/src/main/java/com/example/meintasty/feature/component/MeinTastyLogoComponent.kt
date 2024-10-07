package com.example.meintasty.feature.component

import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.meintasty.R


@Composable
fun MeinTastyLogoComponent(onClick: () -> Unit, img: Painter) {
    Box(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
            .background(Color.White)
    ) {
       Icon(
           painter = img,
           contentDescription = "",

       )

    }
}