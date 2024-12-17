package com.example.meintasty.feature.restaurant_feature.restaurant_create_menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.meintasty.R
import com.example.meintasty.uicomponent.BackIcon
import com.example.meintasty.uicomponent.CustomOutlinedTextField
import com.example.meintasty.uicomponent.HeaderComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantCreateMenuScreen(
    navController: NavController,
    restaurantCreateMenuViewModel: RestaurantCreateMenuViewModel = hiltViewModel()
) {

    val createMenuState = restaurantCreateMenuViewModel.createMenuState.collectAsState()
    var menuName = remember {
        mutableStateOf("")
    }
    var menuPrice = remember {
        mutableStateOf("")
    }
    var menuContent = remember {
        mutableStateOf("")
    }
    var currency = remember {
        mutableStateOf("CHF")
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
                        HeaderComponent(text = stringResource(id = R.string.create_menu))
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

                ) {
                CustomOutlinedTextField(
                    value = menuName.value,
                    onValueChange = { menuName.value = it },
                    placeholder = stringResource(id = R.string.menu_name),
                    imeAction = ImeAction.Next
                )

                CustomOutlinedTextField(
                    value = menuContent.value,
                    onValueChange = { menuContent.value = it },
                    placeholder = stringResource(id = R.string.menu_content),
                    imeAction = ImeAction.Next
                )

                CustomOutlinedTextField(
                    value = menuPrice.value,
                    onValueChange = { menuPrice.value = it },
                    placeholder = stringResource(id = R.string.menu_price),
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Number
                )

                CustomOutlinedTextField(
                    value = currency.value,
                    onValueChange = { currency.value = it },
                    placeholder = stringResource(id = R.string.currency),
                    imeAction = ImeAction.Done,
                )
                //category için  dropdown eklenecek
                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.mein_tasty_color)
                    )
                    ) {
                    Text(text = "CREATE MENU")

                }
            }
        }
    )
}

@Preview
@Composable
fun MenuCreatePrew(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    RestaurantCreateMenuScreen(navController = navController)
}