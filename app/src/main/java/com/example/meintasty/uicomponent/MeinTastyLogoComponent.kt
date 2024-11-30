package com.example.meintasty.uicomponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter


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