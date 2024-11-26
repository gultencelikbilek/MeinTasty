package com.example.meintasty.uicomponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.meintasty.R

@Composable
fun OrderCardComponent(
    modifier: Modifier = Modifier,
  //  order: Order
) {
    //Text(text = order.name.toString())

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        Column {
            Row(
                modifier = modifier.fillMaxWidth()
            ) {
                Image(painter = painterResource(
                    id = R.drawable.food_one),
                    contentDescription = ""
                )
                Column(
                    modifier = Modifier.padding(top = 6.dp)
                ) {
                    Row {
                      //  Text(text = order.name.toString())
                      //  Spacer(modifier = Modifier.width(8.dp))
                      //  Text(text = order.price.toString())
                    }
                    //Text(text = order.orderDate.toString())
                }
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = stringResource(id = R.string.repeat_order))
            }
        }
    }
}