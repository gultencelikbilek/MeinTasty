package com.example.meintasty.feature.basket_screen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.meintasty.R
import com.example.meintasty.data.Constants
import com.example.meintasty.domain.model.get_basket_model.get_basket_request.GetBasketRequest
import com.example.meintasty.domain.model.remove_basket_model.remove_basket_request.RemoveBasketRequest
import com.example.meintasty.domain.model.update_basket_model.update_basket_request.UpdateBasketRequest
import com.example.meintasty.uicomponent.BackIcon
import com.example.meintasty.uicomponent.BasketCardComponent
import com.example.meintasty.uicomponent.HeaderComponent
import com.kevinnzou.swipebox.SwipeBox
import com.kevinnzou.swipebox.SwipeDirection
import com.kevinnzou.swipebox.widget.SwipeIcon
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun BasketScreen(
    navController: NavController, basketViewModel: BasketViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val sharedPreferences =
        context.getSharedPreferences(Constants.SHARED_RESTAURANT_ID, Context.MODE_PRIVATE)
    val restaurant_id = sharedPreferences.getInt(Constants.SHARED_RESTAURANT_ID, 0)

    val userState = basketViewModel.userState.collectAsState().value.data
    val basketState = basketViewModel.basketState.collectAsState()
    val removeBasketState = basketViewModel.removeBasketState.collectAsState()

    val openDialogState = remember { mutableStateOf(false) }

    var totalPrice = basketState.value.data?.sumOf { basketItem ->
        val quantity = basketItem?.quantity ?: 0
        val price = basketItem?.price?.toDouble() ?: 0.0
        quantity * price
    } ?: 0.0


    userState?.userId.let { user_id ->
        val getBasketRequest = GetBasketRequest(restaurant_id, userId = user_id)
        Log.d("getbasket","$user_id")
        LaunchedEffect(user_id) {
            basketViewModel.getBasket(getBasketRequest)
        }
    }

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BackIcon {
                        navController.navigateUp()
                    }
                    HeaderComponent(text = stringResource(id = R.string.basket))
                }
            }, colors = TopAppBarDefaults.topAppBarColors(
                containerColor = colorResource(id = R.color.mein_tasty_color)
            )
        )
    }, content = { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            if (basketState.value.isLoading == true) {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = colorResource(id = R.color.mein_tasty_color)
                    )
                }
            } else {
                val basketData = basketState.value.data
                if (basketData.isNullOrEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Your basket is empty",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Button(
                                onClick = {
                                    navController.navigateUp()
                                },
                                modifier = Modifier.padding(top = 16.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = colorResource(id = R.color.mein_tasty_color)
                                )
                            ) {
                                Text(text = "Go Back to Shopping")
                            }
                        }
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.weight(1f)
                    ) {
                        items(basketData) { basketItem ->
                            basketItem?.let { basket ->
                                Log.d("getbasket","$basket")
                                var count by remember { mutableStateOf(0) }
                                val coroutineScope = rememberCoroutineScope()
                                SwipeBox(
                                    modifier = Modifier
                                        .wrapContentSize()
                                        .padding(8.dp),
                                    swipeDirection = SwipeDirection.EndToStart,
                                    endContentWidth = 60.dp,
                                    endContent = { swipeableState, endSwipeProgress ->
                                        SwipeIcon(
                                            imageVector = Icons.Outlined.Delete,
                                            contentDescription = "Delete",
                                            tint = Color.White,
                                            background = Color(0xFFFA1E32),
                                            weight = 1f,
                                            iconSize = 20.dp
                                        ) {
                                            coroutineScope.launch {
                                                swipeableState.animateTo(0)
                                            }
                                            val removeBasketRequest =
                                                RemoveBasketRequest(restaurant_id)
                                            basketViewModel.removeBasket(removeBasketRequest)
                                            Toast.makeText(
                                                context, "Item Deleted", Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }) { _, _, _ ->
                                    BasketCardComponent(
                                        basket = basket,
                                        onClick = {},
                                        onLongClick = { openDialogState.value = true },
                                        count = count,
                                        onProductAdd = {

                                                val updateBasketRequest = UpdateBasketRequest(
                                                    basketId = basket.id, quantity =basket.quantity
                                                )
                                                basketViewModel.updateBasket(updateBasketRequest)

                                        },
                                        onProductMinus = {
                                            count--
                                        })
                                }
                            }
                        }
                    }
                }
            }

            if (openDialogState.value) {
                AlertDialogBasket(openDialogState = openDialogState)
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = "Total Price: $totalPrice",
                    onValueChange = {},
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        textAlign = TextAlign.Center
                    ),
                    enabled = false,
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White, disabledIndicatorColor = Color.Transparent
                    )
                )

                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.mein_tasty_color),
                    )
                ) {
                    Text(text = stringResource(id = R.string.confirm_cart))
                }
            }
        }
    })
}


@Composable
fun AlertDialogBasket(openDialogState: MutableState<Boolean>) {
    if (openDialogState.value) {
        AlertDialog(onDismissRequest = {
            openDialogState.value = false
        }, title = {
            Text(
                text = stringResource(id = R.string.delete_alert), style = TextStyle(
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    color = Color.Black
                )
            )
        }, confirmButton = {
            TextButton(onClick = {
                openDialogState.value = false

            }) {
                Text(text = stringResource(id = R.string.delete), color = Color.Black)
            }

        }, dismissButton = {
            TextButton(onClick = {
                openDialogState.value = false
            }) {
                Text(text = stringResource(id = R.string.cancel), color = Color.Black)
            }
        }, containerColor = Color.White
        )
    }
}

@Preview
@Composable
fun CartScreenPrew() {
    //BasketScreen()
}