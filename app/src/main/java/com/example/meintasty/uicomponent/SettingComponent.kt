package com.example.meintasty.uicomponent

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.meintasty.R


@Composable
fun SettingComponent(onClick: () -> Unit, icon: Painter) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .size(48.dp)
            .background(Color(0xffd63384), RoundedCornerShape(8.dp))
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color(0xffd63384), RoundedCornerShape(12.dp))
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = icon,
                contentDescription = stringResource(
                    id = R.string.back
                ),
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}