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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.meintasty.R
import com.example.meintasty.data.Constants
import com.example.meintasty.domain.model.get_basket_model.get_basket_request.GetBasketRequest
import com.example.meintasty.uicomponent.BackIcon
import com.example.meintasty.uicomponent.BasketCardComponent
import com.example.meintasty.uicomponent.HeaderComponent
import com.example.meintasty.uicomponent.SwipeToDeleteContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasketScreen(
    navController: NavController,
    basketViewModel: BasketViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    val sharedPreferences =
        context.getSharedPreferences(Constants.SHARED_RESTAURANT_ID, Context.MODE_PRIVATE)
    val restaurant_id = sharedPreferences.getInt(Constants.SHARED_RESTAURANT_ID, 0)

    val userState = basketViewModel.userState.collectAsState().value.data
    val basketState = basketViewModel.basketState.collectAsState()

    val openDialogState = remember {

        mutableStateOf(false)
    }
    
    
    Log.d("basketState:","$basketState")
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
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.mein_tasty_color)
                )
            )
        },
        content = { paddingValues ->
            if (basketState.value.isLoading == true){
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = colorResource(id = R.color.mein_tasty_color)
                    )
                    Log.d("basketList:","hereloading")
                }
            }else{
                Log.d("basketList:","heresuccess")

                Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                    val basketState = basketViewModel.basketState.collectAsState()
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        basketState.value.data?.let { basketList ->
                            Log.d("basketList:","$basketList")
                            if (basketList.isNotEmpty()) {
                                items(basketList) { basketItem ->
                                    basketItem?.let { basket ->

                                        SwipeToDeleteContainer(
                                            item = basket,
                                            onDelete = { deletedBasket ->
                                                // Silme işlemi burada yapılabilir
                                            }
                                        ) {
                                            BasketCardComponent(
                                                basket = it,
                                                onClick = {},
                                                onLongClick = {
                                                    openDialogState.value = true
                                                }
                                            )
                                        }
                                    }
                                }
                            } else {
                                Toast.makeText(context, "Basket is Empty", Toast.LENGTH_SHORT).show()
                            }
                        } ?: run {
                            Toast.makeText(context, "Loading..", Toast.LENGTH_SHORT).show()
                        }
                    }

                } }
            if (openDialogState.value ){
                AlertDialogBasket(openDialogState = openDialogState)
            }
        }

    )
}


@Composable
fun AlertDialogBasket(openDialogState: MutableState<Boolean>) {
    if (openDialogState.value){
        AlertDialog(onDismissRequest = {
            openDialogState.value = false
        },
            title = {
                Text(
                    text = stringResource(id = R.string.delete_alert),
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        color = Color.Black)
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    openDialogState.value = false

                }) {
                    Text(text = stringResource(id = R.string.delete), color = Color.Black)
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

@Preview
@Composable
fun CartScreenPrew() {
    //BasketScreen()
}