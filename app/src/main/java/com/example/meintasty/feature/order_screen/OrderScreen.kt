package com.example.meintasty.feature.order_screen

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.meintasty.R
import com.example.meintasty.data.Constants
import com.example.meintasty.domain.model.get_order_model.get_order_request.GetOrderRequest
import com.example.meintasty.navigation.Screen
import com.example.meintasty.uicomponent.BackIcon
import com.example.meintasty.uicomponent.HeaderComponent
import com.example.meintasty.uicomponent.OrderCardComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    orderViewModel: OrderViewModel = hiltViewModel()
) {
    val getOrderState = orderViewModel.getOrderState.collectAsState()
    val context = LocalContext.current
    val userDatabaseState = orderViewModel.userDatabaseState.collectAsState()

    val sharedPreferences =
        context.getSharedPreferences(Constants.SHARED_RESTAURANT_ID, Context.MODE_PRIVATE)
    val restaurantId = sharedPreferences.getInt(Constants.SHARED_RESTAURANT_ID, 0)
    val scrollState = rememberLazyGridState()
    val isLoading = remember { mutableStateOf(false) }
    val userId = userDatabaseState.value.data?.userId

    val fetchNextPage = remember {
        derivedStateOf {
            val totalItemCount = scrollState.layoutInfo.totalItemsCount
            val lastDisplayedIndex = scrollState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            lastDisplayedIndex >= totalItemCount - 1
        }
    }



    LaunchedEffect(Unit) {
        orderViewModel.getUserDatabaseModel()
    }


    LaunchedEffect(userId) {
        userId?.let {
            if (it > 0) {
                val getOrderRequest = GetOrderRequest( restaurantId =restaurantId , pageNumber = 1)
                orderViewModel.getOrder(getOrderRequest)
            }
        }
    }

    Scaffold(
        topBar = {
            androidx.compose.material3.TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        BackIcon(onClick = {
                            navController.navigate(Screen.ProfileScreen.route)
                        })
                        Spacer(modifier = Modifier.width(6.dp))
                        HeaderComponent(text = stringResource(id = R.string.order_screen))

                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.mein_tasty_color),
                )
            )
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                getOrderState.value.data?.let {orderList ->
                   // val orderListNotNull = orderList.value?.filterNotNull() ?: emptyList()
                // items(){
                    //    Log.d("order","$it")
                  //      Text(text = it.name.toString())
                       // OrderCardComponent(modifier,it)
                //    }
                }
            }
        }
    )
}

