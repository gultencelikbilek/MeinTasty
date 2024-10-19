package com.example.meintasty.feature.basket_screen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.meintasty.R
import com.example.meintasty.data.Constants
import com.example.meintasty.domain.model.get_basket_model.get_basket_request.GetBasketRequest
import com.example.meintasty.uicomponent.BackIcon
import com.example.meintasty.uicomponent.BasketCardComponent
import com.example.meintasty.uicomponent.HeaderComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasketScreen(
    navController: NavController,
    basketViewModel: BasketViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val sharedPreferences =
        context.getSharedPreferences(Constants.SHARED_RESTAURANT_ID, Context.MODE_PRIVATE)
    val restaurant_id = sharedPreferences.getString(Constants.SHARED_RESTAURANT_ID, null)
    val userState = basketViewModel.userState.collectAsState().value.data
    val basketState = basketViewModel.basketState.collectAsState()

    userState?.let { user ->
        restaurant_id.let { restaurant_id ->
            val getBasketRequest = GetBasketRequest(restaurant_id?.toInt(), user.userId)
            Log.d("getBasketRequest:", "$getBasketRequest")
            LaunchedEffect(basketState) {
                basketViewModel.getBasket(getBasketRequest)
                Log.d("getBasketRequest:", "$basketState")
            }
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
                        BackIcon {
                            navController.navigateUp()
                        }
                        HeaderComponent(text = stringResource(id = R.string.basket))
                    }
                })
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (basketState.value.data == null) {
                    Text(
                        text = stringResource(id = R.string.basket_epmty)
                    )
                } else {
                    val basketState = basketViewModel.basketState.collectAsState()

                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        basketState.value.data?.let { basketList ->
                            if (basketList.isNotEmpty()) {
                                items(basketList) { basketItem ->
                                    basketItem?.let { basket ->
                                        BasketCardComponent(basket = basket)
                                    }
                                }
                            } else {
                                Toast.makeText(context,"Basket is Empty",Toast.LENGTH_SHORT).show()
                            }
                        } ?: run {
                            Toast.makeText(context,"Basket is Empty",Toast.LENGTH_SHORT).show()

                        }
                    }
                }
            }
        }
    )
}


@Preview
@Composable
fun CartScreenPrew() {
    //BasketScreen()
}