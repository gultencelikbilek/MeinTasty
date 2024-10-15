package com.example.meintasty.uicomponent

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun LabelUserText(text:String) {

    Row(
        modifier = Modifier.wrapContentSize()
    ) {
        Text(text = text,
            modifier = Modifier,
            style = TextStyle(
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                color = Color.DarkGray,
                fontWeight = FontWeight.Bold
            )
        )

    }

}