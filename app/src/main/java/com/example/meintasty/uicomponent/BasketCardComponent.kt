package com.example.meintasty.uicomponent


import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.meintasty.R
import com.example.meintasty.domain.model.get_basket_model.get_basket_response.Basket
import com.example.meintasty.feature.basket_screen.BasketViewModel
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BasketCardComponent(
    basket: Basket?,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    onProductAdd: () -> Unit,
    onProductMinus: () -> Unit,
    basketViewModel: BasketViewModel
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    // Her ürün için toplam fiyatı hesapla
    val productTotalPrice = remember(basket?.id, basket?.quantity, basket?.price) {
        val quantity = basket?.quantity ?: 0
        val price = basket?.price?.replace(",", ".")?.trim()?.toDoubleOrNull() ?: 0.0
        quantity * price
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable(onClick = onClick)
                .combinedClickable(
                    onClick = onClick,
                    onLongClick = onLongClick
                ),
            elevation = CardDefaults.cardElevation(1.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .padding(8.dp)
                        .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.food_one),
                        contentDescription = "Food Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.matchParentSize()
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                ) {
                    Text(
                        text = basket?.menuName.orEmpty(),
                        style = MaterialTheme.typography.titleMedium.copy(color = Color.Black),
                        modifier = Modifier.wrapContentWidth()
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = basket?.restaurantName.orEmpty(),
                        style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray),
                        maxLines = 1
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(end = 4.dp)
                ) {
                    Text(
                        text = "${basket?.price}",
                        style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .background(Color.White)
                            .border(1.dp, Color.LightGray, shape = RoundedCornerShape(50.dp))
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {
                                expanded = !expanded
                            }
                            .animateContentSize()
                            .height(30.dp)
                            .wrapContentWidth()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Cengit statusgter,
                            modifier = Modifier.padding(4.dp)
                        ) {
                            if (basket?.quantity!! > 1) {
                                Icon(
                                    painter = painterResource(id = R.drawable.minus),
                                    contentDescription = "Decrease",
                                    modifier = Modifier
                                        .size(16.dp)
                                        .clickable { onProductMinus() }
                                )
                            } else if (basket?.quantity == 1) {
                                Icon(
                                    painter = painterResource(id = R.drawable.delete),
                                    contentDescription = "Decrease",
                                    modifier = Modifier
                                        .size(16.dp)
                                        .clickable { onProductMinus() }
                                )
                            }

                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "${basket?.quantity ?: 0}",
                                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Black)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(
                                painter = painterResource(id = R.drawable.plus),
                                contentDescription = "Increase",
                                modifier = Modifier
                                    .size(16.dp)
                                    .clickable { onProductAdd() }
                            )
                        }
                    }
                }
            }
        }
    }
}


