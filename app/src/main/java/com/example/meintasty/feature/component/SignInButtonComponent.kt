package com.example.meintasty.feature.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SignInButtonComponent(
    onClick: () -> Unit,
    text:String
) {
    Button(
        onClick = { onClick() },
        content = {
            Text(text = text)
        },
        modifier = Modifier.width(100.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xffdc3545),
            contentColor = Color.White
        )
    )

}

@Preview
@Composable
fun SignInButtonPrew() {
    
}