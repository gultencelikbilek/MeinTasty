package com.example.meintasty.uicomponent

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.meintasty.R

@Composable
fun SearchButton(onClick :() -> Unit) {

    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xffdc3545)
        ),
        modifier = Modifier.fillMaxWidth().wrapContentHeight(),
        elevation = ButtonDefaults.elevatedButtonElevation(20.dp)
    ) {
        Text(
            text = stringResource(id = R.string.search)
        )

    }

}