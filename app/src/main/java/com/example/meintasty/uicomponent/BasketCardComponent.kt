package com.example.meintasty.uicomponent


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
    val context = LocalContext.current
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
                    text = "279",
                    style = TextStyle(
                        color = Color.DarkGray,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize
                    ),
                    modifier = Modifier.padding(vertical = 25.dp)
                )

                Spacer(modifier = Modifier.width(10.dp))

                Box(
                    modifier = Modifier
                        .width(60.dp)
                        .border(1.dp, Color.LightGray, shape = RoundedCornerShape(50.dp))
                        .padding(4.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.minus),
                            contentDescription = "",
                            modifier = Modifier
                                .size(16.dp)
                                .clickable {
                                    onProductAdd()
                                }
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text = basket?.quantity.toString(),
                            style = TextStyle(
                                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                                color = Color.Black
                            )
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.plus),
                            contentDescription = "",
                            modifier = Modifier
                                .size(16.dp)
                                .clickable {
                                    onProductMinus()
                                }
                        )
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
