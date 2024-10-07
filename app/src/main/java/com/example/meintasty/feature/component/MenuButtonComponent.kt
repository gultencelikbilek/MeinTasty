package com.example.meintasty.feature.component

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.meintasty.R

@Composable
fun MenuButtonComponent(onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        modifier = Modifier.wrapContentSize(),
        content = {
            Text(
                text = stringResource(id = R.string.menu)
            )
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xffdc3545),
            contentColor = Color.White
        )
    )
}

@Preview
@Composable
fun MenuButtonPrew() {
    MenuButtonComponent(onClick = {})
}