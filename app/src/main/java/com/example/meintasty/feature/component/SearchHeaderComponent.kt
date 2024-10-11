package com.example.meintasty.feature.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.meintasty.R

@Composable
fun SearchHeaderComponent(text : String) {
    val customFontFamily = FontFamily(
        Font(resId = R.font.poppins_bold, weight = FontWeight.Thin)
    )
    Text(
        text = text,
        modifier = Modifier.padding(start = 16.dp),
        style = TextStyle(
            color = Color.Black,
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontFamily = customFontFamily
        )
    )

}