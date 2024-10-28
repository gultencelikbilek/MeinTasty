package com.example.meintasty.uicomponent

import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.meintasty.R
import com.example.meintasty.data.Constants
import com.example.meintasty.domain.model.add_basket_model.add_basket_request.AddBasketRequest
import com.example.meintasty.domain.model.restaurant_detail.restaurant_detail_response.Menu
import com.example.meintasty.feature.detail_restaurant.DetailRestaurantViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MenuListCardComponent(menu: Menu?, detailRestaurantViewModel: DetailRestaurantViewModel) {

    val addBasketState = detailRestaurantViewModel.addBasketState.collectAsState().value

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
        Log.d("restaurant_id","${Constants.SHARED_RESTAURANT_ID}")
    }

    Card(
        modifier = Modifier
            .width(85.dp)
            .height(170.dp)
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(1.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .height(150.dp)
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
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, end = 4.dp),

                ) {
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .background(colorResource(id = R.color.white), RoundedCornerShape(12.dp))
                        .clickable {
                            menu?.let { menu ->
                                Log.d("menu","$menu")
                                if (userModelState.data?.userId != null) {
                                    Log.d("menu","$userModelState.data?.userId")
                                    val currentDateTime = LocalDateTime.now()
                                    val formattedDate = currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))
                                    val addBasketRequest = AddBasketRequest(
                                        basketDate = formattedDate,
                                        currencyCode = menu.currency.toString(),
                                        menuId = menu.menuId,
                                        price = menu.price.toString(),
                                        quantity = 1,
                                        restaurantId = menu.restaurantId,
                                        userId = userModelState.data?.userId,
                                    )

                                    Log.d("restaurantId:", "${menu.restaurantId}")
                                    Log.d("restaurant:", "${userModelState.data?.userId}")
                                    Log.d("addBasketRequest:", "$addBasketRequest")
                                    detailRestaurantViewModel.addBasket(addBasketRequest)
                                    Toast
                                        .makeText(context, "added basket", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        }
                        .border(1.dp, Color.LightGray, RoundedCornerShape(25.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.plus),
                        contentDescription = stringResource(id = R.string.add),
                        tint = Color.Black,
                        modifier = Modifier.size(10.dp)

                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = menu?.menuName.toString(),
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        color = Color.Black,
                        fontFamily = customFontFamily
                    ),
                    modifier = Modifier.padding(bottom = 2.dp) // Space between texts
                )
                Text(
                    text = menu?.price.toString(),
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        color = Color.Gray
                    ),
                    modifier = Modifier.padding(bottom = 2.dp)
                )
                Text(
                    text = menu?.menuPic.toString(),
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        color = Color.Gray
                    )
                )
            }
        }
    }
}