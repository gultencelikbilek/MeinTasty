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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.meintasty.R
import com.example.meintasty.domain.model.get_basket_model.get_basket_response.Basket

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BasketCardComponent(
    basket: Basket?,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    onProductAdd: () -> Unit,
    onProductMinus: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val isLoading = remember { mutableStateOf(false) }


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
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
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(modifier = Modifier.wrapContentSize()) {
                Image(
                    painter = painterResource(id = R.drawable.food_one),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier.padding(start = 2.dp, top = 8.dp)
            ) {
                Text(
                    text = basket?.menuName.toString(),
                    style = TextStyle(
                        color = Color.DarkGray,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = basket?.restaurantName.toString(),
                    style = TextStyle(
                        color = Color.DarkGray,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize
                    )
                )
            }
            Spacer(modifier = Modifier.width(8.dp));
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${basket?.price}",
                    style = TextStyle(
                        color = Color.DarkGray,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize
                    ),
                    modifier = Modifier.padding(vertical = 25.dp)
                )

                Spacer(modifier = Modifier.width(10.dp))

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
                        .wrapContentHeight()
                        .fillMaxWidth(if (expanded) 0.8f else 0.4f)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        if (expanded) {
                            Icon(
                                painter = painterResource(id = R.drawable.minus),
                                contentDescription = "Decrease",
                                modifier = Modifier
                                    .size(16.dp)
                                    .clickable { onProductMinus() }
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                        }
                        Text(
                            text = "${basket?.quantity}",
                            style = TextStyle(
                                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                                color = Color.Black
                            )
                        )
                        if (expanded) {
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(
                                painter = painterResource(id = R.drawable.plus),
                                contentDescription = "Increase",
                                modifier = Modifier
                                    .size(16.dp)
                                    .clickable {
                                        onProductAdd()
                                    }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun BasketPrew() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(1.dp),
    ) {
        Icon(
            painter = painterResource(id = R.drawable.plus),
            contentDescription = "",
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = "0",
            style = TextStyle(
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                color = Color.Black
            )
        )
        Icon(
            painter = painterResource(id = R.drawable.minus),
            contentDescription = "",
            modifier = Modifier.size(20.dp)
        )

    }
}
