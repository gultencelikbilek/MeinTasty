package com.example.meintasty.uicomponent

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.meintasty.R

@Composable
fun HotTodayComponent() {
    Text(
        text = stringResource(id = R.string.what_hot_today),
        style = TextStyle(
            color = Color.Black,
            fontWeight = FontWeight.Normal,
            fontSize = MaterialTheme.typography.titleLarge.fontSize
        )
    )
}