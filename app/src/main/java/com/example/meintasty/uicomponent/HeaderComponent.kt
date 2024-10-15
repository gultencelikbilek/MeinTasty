package com.example.meintasty.uicomponent

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.meintasty.R

@Composable
fun HeaderComponent(text : String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 60.dp),
        text = text,
        style = TextStyle(
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = MaterialTheme.typography.titleLarge.fontSize
        )
    )
}