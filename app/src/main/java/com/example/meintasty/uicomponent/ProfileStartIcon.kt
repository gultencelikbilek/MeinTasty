package com.example.meintasty.uicomponent

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.meintasty.R

@Composable
fun ProfileStartIcon(
    modifier: Modifier = Modifier,
    onClick:() -> Unit,
    painter: Painter
) {
    Box(
        modifier = modifier
            .size(56.dp)
            .background(
                colorResource(id = R.color.white),
                RoundedCornerShape(8.dp)
            )
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(
                    colorResource(id = R.color.white),
                    RoundedCornerShape(12.dp)
                )
                .clickable {
                    onClick()
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painter,
                contentDescription = stringResource(
                    id = R.string.back
                ),
                tint = colorResource(id = R.color.mein_tasty_color),
                modifier = Modifier.size(36.dp)
            )
        }
    }

}