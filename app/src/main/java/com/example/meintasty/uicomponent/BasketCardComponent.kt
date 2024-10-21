package com.example.meintasty.uicomponent

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.rememberDismissState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissState
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BasketCardComponent(
    basket: Basket?,
    onClick: () -> Unit,
    onLongClick: () -> Unit
) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
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
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                //text = basket?.price.toString(),// null
                text = "279",
                style = TextStyle(
                    color = Color.DarkGray,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize
                ),
                modifier = Modifier.padding(vertical = 25.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))

            IconButton(onClick = {
                Toast.makeText(context,"Delete",Toast.LENGTH_SHORT).show()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.delete),
                    contentDescription = "",
                    modifier = Modifier
                        .size(24.dp)
                )
            }


        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> SwipeToDeleteContainer(
    item: T,
    onDelete: (T) -> Unit,
    animationDuration: Int = 500,
    content: @Composable (T) -> Unit,
) {
    var isRemoved by remember { mutableStateOf(false) }
    val state = rememberDismissState(
        confirmValueChange = { value ->
            // Kaydırma eylemi sonunda silme işlemini onaylayalım
            if (value == DismissValue.DismissedToEnd) {
                isRemoved = true
                true
            } else {
                false
            }
        }
    )

    LaunchedEffect(key1 = isRemoved) {
        if (isRemoved) {
            delay(animationDuration.toLong())
            onDelete(item)
        }
    }

    AnimatedVisibility(
        visible = !isRemoved,
        exit = shrinkVertically(
            tween(animationDuration),
            shrinkTowards = Alignment.Top
        ) + fadeOut()
    ) {
        SwipeToDismiss(
            state = state,
            background = {
                SwipeDelete(state)
            },
            dismissContent = { content(item) },
            directions = setOf(DismissDirection.StartToEnd, DismissDirection.EndToStart),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeDelete(
    swipeDismissState: DismissState
) {
    val color = if (swipeDismissState.dismissDirection == DismissDirection.StartToEnd) {
        Color.Red // Soldan sağa kaydırıldığında kırmızı
    } else {
        Color.Transparent // Diğer durumlarda şeffaf
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
            .padding(16.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.delete),
            contentDescription = null,
            tint = Color.White
        )
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
        }
    }
}