package com.example.meintasty.uicomponent

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.meintasty.R

@Composable
fun ProfileStartIconComponent(
    modifier: Modifier = Modifier,
    onClick:() -> Unit,
    painter: Painter
) {
    Box(
        modifier = modifier
            .size(dimensionResource(id = R.dimen.user_card_component_card_size))
            .background(
                colorResource(id = R.color.white),
                RoundedCornerShape(dimensionResource(id = R.dimen.user_card_component_corner_shape))
            )
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.profil_start_icon_icon_size))
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
                painter = painter,
                contentDescription = stringResource(
                    id = R.string.back
                ),
                tint = colorResource(id = R.color.mein_tasty_color),
                modifier = Modifier.size(dimensionResource(id = R.dimen.profil_start_icon_icon_size))
            )
        }
    }

}