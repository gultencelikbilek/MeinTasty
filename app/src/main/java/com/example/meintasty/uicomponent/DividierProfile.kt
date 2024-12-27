package com.example.meintasty.uicomponent

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.meintasty.R

@Composable
fun DividierProfile(modifier: Modifier = Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Divider(
            modifier = modifier
                .weight(1f)
                .fillMaxWidth(),
            color = Color.LightGray,
            thickness = dimensionResource(id = R.dimen.divide_thicknes)
        )
    }
}