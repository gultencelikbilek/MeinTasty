package com.example.meintasty.feature.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.meintasty.R

@Composable
fun BackIcon(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .background(colorResource(id = R.color.mein_tasty_color), RoundedCornerShape(8.dp))
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .background(colorResource(id = R.color.mein_tasty_color), RoundedCornerShape(12.dp))
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.back),
                contentDescription = stringResource(
                    id = R.string.back
                ),
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}


@Preview
@Composable
fun BackIconPrew(modifier: Modifier = Modifier) {
    BackIcon(onClick = {})
}