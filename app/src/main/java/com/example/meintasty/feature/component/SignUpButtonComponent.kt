package com.example.meintasty.feature.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun SignUpButtonComponent(
    onClick: () -> Unit,
    text:String
) {
    Box(modifier = Modifier.wrapContentWidth()) {
        Button(
            onClick = { onClick() },
            content = {
                Text(text = text)
            },
            border = BorderStroke(1.dp,Color(0xffdc3545)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .wrapContentHeight(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.DarkGray
            )
        )
    }
}