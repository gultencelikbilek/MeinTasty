package com.example.meintasty.feature.basket_screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.meintasty.R
import com.example.meintasty.data.Constants
import com.example.meintasty.domain.model.get_basket_model.get_basket_request.GetBasketRequest
import com.example.meintasty.domain.model.remove_basket_model.remove_basket_request.RemoveBasketRequest
import com.example.meintasty.navigation.Screen
import com.example.meintasty.uicomponent.BackIcon
import com.example.meintasty.uicomponent.BasketCardComponent
import com.example.meintasty.uicomponent.HeaderComponent
import com.kevinnzou.swipebox.SwipeBox
import com.kevinnzou.swipebox.SwipeDirection
import com.kevinnzou.swipebox.widget.SwipeIcon
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun BasketScreen(
    navController: NavController,
    basketViewModel: BasketViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val sharedPreferences =
        context.getSharedPreferences(Constants.SHARED_RESTAURANT_ID, Context.MODE_PRIVATE)
    val restaurantId = sharedPreferences.getInt(Constants.SHARED_RESTAURANT_ID, 0)
    var selectedBasketId by remember { mutableStateOf(0) }
    val userState = basketViewModel.userState.collectAsState().value.data
    val basketState = basketViewModel.basketState.collectAsState()
    val totalPrice by basketViewModel.totalPriceState.collectAsState()
    val openDialogState = remember { mutableStateOf(false) }
    val basketData = basketState.value.data
    val isLoading = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        basketViewModel.refreshBasket()
    }

    userState?.userId?.let { userId ->
        val getBasketRequest = GetBasketRequest(restaurantId, userId)
        LaunchedEffect(userId) {
            basketViewModel.getBasket(getBasketRequest)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BackIcon { navController.navigateUp() }
                        HeaderComponent(text = stringResource(id = R.string.basket))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.mein_tasty_color)
                )
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                if (basketState.value.isLoading == true) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = colorResource(id = R.color.mein_tasty_color))
                    }
                } else {
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
                                    onClick = { navController.navigate(Screen.RestaurantScreen.route) },
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
                        LazyColumn(modifier = Modifier.weight(1f)) {
                            items(basketData) { basketItem ->
                                basketItem?.let { basket ->

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
                                                    RemoveBasketRequest(basketId = basket.id)
                                                basketViewModel.removeBasket(removeBasketRequest)
                                                Toast.makeText(
                                                    context,
                                                    "Item Deleted",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        }
                                    ) { _, _, _ ->
                                        BasketCardComponent(
                                            basket = basket,
                                            onClick = {
                                            },
                                            onLongClick = {
                                                selectedBasketId = basket.id!! //alertdialog için
                                                openDialogState.value = true
                                            },
                                            onProductAdd = {
                                              //  basket.quantity = (basket.quantity ?: 0) + 1
                                                basket.id?.let {
                                                    basketViewModel.updateQuantity(
                                                        it,
                                                        1
                                                    )
                                                }
                                            },
                                            onProductMinus = {
                                               // basket.quantity = basket.quantity!! - 1
                                                if (basket.quantity!! > 0) {
                                                    basket.id?.let {
                                                        basketViewModel.updateQuantity(
                                                            it,
                                                           -1
                                                        )
                                                    }
                                                } else {
                                                    isLoading.value = true
                                                    if (basket.quantity!! == 0) {
                                                        val removeBasketRequest =
                                                            RemoveBasketRequest(basketId = basket.id)
                                                        basketViewModel.removeBasket(
                                                            removeBasketRequest
                                                        )
                                                        isLoading.value = false
                                                        Toast.makeText(
                                                            context,
                                                            "Item Deleted",
                                                            Toast.LENGTH_SHORT
                                                        ).show()
                                                    }
                                                }
                                            },
                                            basketViewModel
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                if (openDialogState.value && selectedBasketId != null) {
                    AlertDialogBasket(
                        openDialogState = openDialogState,
                        basketViewModel = basketViewModel,
                        selectedId = selectedBasketId
                    )
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
                        onClick = {
                            navController.navigate(Screen.PaymentScreen.route)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.mein_tasty_color)
                        )
                    ) {
                        Text(text = stringResource(id = R.string.confirm_cart))
                    }
                }
            }
        }
    )
}

@Composable
fun AlertDialogBasket(
    openDialogState: MutableState<Boolean>,
    basketViewModel: BasketViewModel,
    selectedId: Int? // selectedId parametresi ekleniyor
) {
    val context = LocalContext.current
    if (openDialogState.value) {
        AlertDialog(
            onDismissRequest = { openDialogState.value = false },
            title = {
                Text(
                    text = stringResource(id = R.string.delete_alert),
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        color = Color.Black
                    )
                )
            },
            confirmButton = {
                TextButton(onClick = {

                    val removeBasketRequest = RemoveBasketRequest(basketId = selectedId)
                    basketViewModel.removeBasket(removeBasketRequest)
                    openDialogState.value = false
                    Toast.makeText(context, "Item Deleted", Toast.LENGTH_SHORT).show()
                }) {
                    Text(text = stringResource(id = R.string.delete), color = Color.Black)
                }
            },
            dismissButton = {
                TextButton(onClick = { openDialogState.value = false }) {
                    Text(text = stringResource(id = R.string.cancel), color = Color.Black)
                }
            },
            containerColor = Color.White
        )
    }
}
