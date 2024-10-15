package com.example.meintasty.uicomponent

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun BasicText(
    modifier: Modifier = Modifier,
    text:String
) {
    Row(
        modifier = Modifier.wrapContentWidth()
    ) {
        Text(text = text,
            modifier = modifier.padding(top = 16.dp),
            style = TextStyle(
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                color = Color.LightGray,
                fontWeight = FontWeight.Bold
            )
        )
    }

}