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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.meintasty.R

@Composable
fun EditIconComponent(
    onClick:() -> Unit,
    icon: Painter,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .padding(end = dimensionResource(id = R.dimen.horizontal_padding))
            .size(
                dimensionResource(id = R.dimen.box_size)
            )
            .background(
                colorResource(id = R.color.white),
                RoundedCornerShape(dimensionResource(id = R.dimen.icon_corner_shape))
            )
            .clickable {

                onClick()

            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = icon,
            contentDescription = stringResource(
                id = R.string.back
            ),
            tint = colorResource(id = R.color.mein_tasty_color),
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.icon_size))
                .align(Alignment.Center)
        )
    }
}