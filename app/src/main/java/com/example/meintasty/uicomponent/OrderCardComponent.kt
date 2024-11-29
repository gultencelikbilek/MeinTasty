package com.example.meintasty.uicomponent

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.meintasty.R
import com.example.meintasty.domain.model.add_basket_model.add_basket_request.AddBasketRequest
import com.example.meintasty.domain.model.add_basket_model.add_basket_response.AddBasket
import com.example.meintasty.domain.model.get_order_model.get_order_response.Order
import com.example.meintasty.feature.order_screen.OrderViewModel

@Composable
fun OrderCardComponent(
    modifier: Modifier = Modifier,
    order: Order,
    orderViewModel: OrderViewModel
) {
    val addBasketState = orderViewModel.addBasketState.collectAsState()
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
            .height(150.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Column {
            Row(
                modifier = modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .background(Color.White)
                        .align(Alignment.CenterVertically)
                        .border(
                            width = 1.dp,
                            color = Color.Transparent,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(4.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.food_one),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(85.dp)
                            .width(85.dp)
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(top = 6.dp)
                        .padding(start = 6.dp)
                ) {
                    Row {
                        Text(
                            text = order.name.toString(),
                            style = MaterialTheme.typography.titleMedium.copy(color = Color.Black),
                            maxLines = 1
                        )
                        Spacer(modifier = Modifier.width(50.dp))
                        Text(
                            text = order.price.toString(),
                            style = MaterialTheme.typography.titleMedium.copy(color = Color.Black),
                            maxLines = 1
                        )
                    }
                    Text(
                        text = order.orderDate.toString(),
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray),
                        maxLines = 1
                    )
                }
            }
            Button(
                 //Burdan ekleme yapınca hata veriyor swagerrda öyle
                onClick = {
                          val addBasketRequest = AddBasketRequest(
                              basketDate = order.orderDate,
                              currencyCode = order.currencyCode,
                              menuId = order.id,
                              price = order.price,
                              quantity = 1,
                              restaurantId = order.restaurantId,
                              userId = order.userId
                          )
                    orderViewModel.addBasket(addBasketRequest)
                    Log.d("addbasket:success","$addBasketRequest")

                },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.mein_tasty_color)
                )
            ) {
                Text(
                    text = stringResource(id = R.string.repeat_order),
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.titleSmall.fontSize,
                        color = Color.White
                    )
                )
            }
        }
    }
}
