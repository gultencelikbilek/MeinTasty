package com.example.meintasty.feature.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.meintasty.R

@Composable
fun SkipComponent(onClick: () -> Unit) {
    Text(
        modifier = Modifier
            .padding(end = 16.dp)
            .clickable {
                onClick()
            },
        text = stringResource(id = R.string.skip),
        style = TextStyle(
            color = Color.White,
            fontSize = MaterialTheme.typography.titleMedium.fontSize
        )
    )
}