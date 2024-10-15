package com.example.meintasty.uicomponent

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.meintasty.R

@Composable
fun ProfileUserIcon(onClick: () -> Unit, painter: Painter) {
    Card(
        modifier = Modifier
            .size(56.dp)
            .clickable {
                onClick()
            },
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = colorResource(id = R.color.white),
                    shape = RoundedCornerShape(8.dp)
                )
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painter,
                contentDescription = stringResource(id = R.string.back),
                tint = colorResource(id = R.color.mein_tasty_color),
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@Preview
@Composable
fun UserIconPrew() {
    ProfileUserIcon(onClick = {}, painter = painterResource(id = R.drawable.user))
}
