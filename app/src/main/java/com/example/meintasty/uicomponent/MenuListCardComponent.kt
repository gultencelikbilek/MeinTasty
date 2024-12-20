package com.example.meintasty.uicomponent

import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.meintasty.R
import com.example.meintasty.data.Constants
import com.example.meintasty.domain.model.add_basket_model.add_basket_request.AddBasketRequest
import com.example.meintasty.domain.model.get_basket_model.get_basket_request.GetBasketRequest
import com.example.meintasty.domain.model.get_basket_model.get_basket_response.Basket
import com.example.meintasty.domain.model.restaurant_detail.restaurant_detail_response.Menu
import com.example.meintasty.feature.user_feature.detail_restaurant.DetailRestaurantViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SharedTransitionScope.MenuListCardComponent(
    animatedVisibilityScope: AnimatedVisibilityScope,
    menu: Menu?,
    basketControlState: List<Basket?>?,
    restaurantId: Int,
    detailRestaurantViewModel: DetailRestaurantViewModel
) {

    val addBasketState = detailRestaurantViewModel.addBasketState.collectAsState().value
    val deatilState = detailRestaurantViewModel.detailRestState.collectAsState().value
    val mutableInteractionSource = remember { MutableInteractionSource() }
    val pressed = mutableInteractionSource.collectIsPressedAsState()
    val openDialogState = remember { mutableStateOf(false) }
    var selectedBasketId by remember { mutableStateOf(0) }

    val userModelState = detailRestaurantViewModel.userModelState.collectAsState().value


    val context = LocalContext.current
    val sharedPreferences =
        context.getSharedPreferences(Constants.SHARED_RESTAURANT_ID, Context.MODE_PRIVATE)

    val customFontFamily = FontFamily(
        Font(resId = R.font.poppins_light, weight = FontWeight.Bold)
    )

    LaunchedEffect(Unit) {
        val editorRestaurantId = sharedPreferences.edit()
        editorRestaurantId.putInt(Constants.SHARED_RESTAURANT_ID, 0)
        editorRestaurantId.apply()
        Log.d("restaurant_id", "${Constants.SHARED_RESTAURANT_ID}")
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(1.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.restaurant_bg),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .border(1.dp, Color.Transparent, RoundedCornerShape(25.dp))
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = menu?.menuName.toString(),
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        color = Color.Black,
                        fontFamily = customFontFamily
                    ),
                    modifier = Modifier.padding(bottom = 2.dp)
                )
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .border(1.dp, Color.LightGray, shape = RoundedCornerShape(25.dp))
                        .clickable(
                            interactionSource = mutableInteractionSource,
                            indication = null
                        ) {
                            val baskets =
                                basketControlState?.firstOrNull() // Burada zaten Basket modeline erişiyorsunuz
                            Log.d("addBasketRequest:restaurantId", "${baskets?.restaurantId}")
                            Log.d("addBasketRequest:", "${restaurantId}")

                            menu?.let { menu ->
                                Log.d("menu", "$menu")
                                //   if ()
                                if (basketControlState.isNullOrEmpty().not()) {
                                    if (baskets?.restaurantId == restaurantId) {
                                        Log.d("addBasketRequest:", "${baskets?.restaurantId}")
                                        Log.d("addBasketRequest:", "${restaurantId}")
                                        Log.d("addBasketRequest:", "burda")

                                        if (userModelState.data?.userId != null) {
                                            val addBasketRequest = AddBasketRequest(
                                                currencyCode = menu.currency.toString(),
                                                menuId = menu.id,
                                                price = menu.menuPrice.toString(),
                                                quantity = 1,
                                                restaurantId = restaurantId,// deatilState.data?.restaurantId,
                                                isReplaceBasket = false
                                            )
                                            Log.d("addBasketRequest:", "$addBasketRequest")
                                            detailRestaurantViewModel.addBasket(addBasketRequest)
                                            Toast
                                                .makeText(
                                                    context,
                                                    "addedBasket",
                                                    Toast.LENGTH_SHORT
                                                )
                                                .show()
                                        }

                                    } else {
                                        openDialogState.value = true
                                        if (userModelState.data?.userId != null) {
                                            Log.d("addBasketRequest:", "burda:else")
                                            val addBasketRequest = AddBasketRequest(
                                                currencyCode = menu.currency.toString(),
                                                menuId = menu.id,
                                                price = menu.menuPrice.toString(),
                                                quantity = 1,
                                                restaurantId = deatilState.data?.restaurantId,
                                                isReplaceBasket = true
                                            )
                                            Log.d("addBasketRequest:", "$addBasketRequest")
                                            detailRestaurantViewModel.addBasket(addBasketRequest)
                                            Toast
                                                .makeText(
                                                    context,
                                                    "addedBasket",
                                                    Toast.LENGTH_SHORT
                                                )
                                                .show()

                                        }
                                    }
                                } else {
                                    if (userModelState.data?.userId != null) {
                                        Log.d("addBasketRequest:", "burda:else")
                                        val addBasketRequest = AddBasketRequest(
                                            currencyCode = menu.currency.toString(),
                                            menuId = menu.id,
                                            price = menu.menuPrice.toString(),
                                            quantity = 1,
                                            restaurantId = deatilState.data?.restaurantId,
                                            isReplaceBasket = true
                                        )
                                        Log.d("addBasketRequest:", "$addBasketRequest")
                                        detailRestaurantViewModel.addBasket(addBasketRequest)
                                        Toast
                                            .makeText(
                                                context,
                                                "addedBasket",
                                                Toast.LENGTH_SHORT
                                            )
                                            .show()

                                    }
                                }
                            }
                        }

                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.plus),
                        contentDescription = "",
                        modifier = Modifier
                            .size(24.dp)
                            .padding(6.dp)
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.Top
            ) {
                val contentVsPrice =
                    if (menu?.menuContent.isNullOrEmpty()) menu?.menuPrice else menu?.menuContent

                if (menu?.menuContent.isNullOrEmpty()) {
                    Text(
                        text = contentVsPrice.toString(),
                        style = TextStyle(
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            color = Color.Gray
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                } else {
                    Text(
                        text = menu?.menuContent.toString(),
                        style = TextStyle(
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            color = Color.Gray
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(bottom = 2.dp)
                    )

                    Text(
                        text = menu?.menuPrice.toString(),
                        style = TextStyle(
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            color = Color.Gray
                        ),
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                }

                Text(
                    text = menu?.menuPic.toString(),
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        color = Color.Gray
                    )
                )

                if (openDialogState.value && selectedBasketId != null) {
                    AlertDialogReplace(
                        openDialogState = openDialogState,
                        detailRestaurantViewModel,
                        menu,
                        selectedId = selectedBasketId
                    )
                }
            }
        }
    }
}


@Composable
fun AlertDialogReplace(
    openDialogState: MutableState<Boolean>,
    detailRestaurantViewModel: DetailRestaurantViewModel,
    menu: Menu?,
    selectedId: Int? // selectedId parametresi ekleniyor

) {
    val context = LocalContext.current
    val userModelState = detailRestaurantViewModel.userModelState.collectAsState().value
    val deatilState = detailRestaurantViewModel.detailRestState.collectAsState().value


    if (openDialogState.value) {
        AlertDialog(
            onDismissRequest = { openDialogState.value = false },
            title = {
                Text(
                    text = stringResource(id = R.string.info_basket),
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        color = Color.Black
                    )
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    openDialogState.value = false

                }) {
                    Text(text = stringResource(id = R.string.change), color = Color.Black)
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    openDialogState.value = false
                }) {
                    Text(text = stringResource(id = R.string.cancel), color = Color.Black)
                }
            },
            containerColor = Color.White
        )
    }
}