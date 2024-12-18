package com.example.meintasty.feature.restaurant_feature.restaurant_create_menu

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.meintasty.R
import com.example.meintasty.data.Constants
import com.example.meintasty.domain.model.create_menu_model.create_menu_request.CreateMenuRequest
import com.example.meintasty.domain.model.restaurant_detail.restaurant_detail_request.DetailRestaurantRequest
import com.example.meintasty.uicomponent.BackIcon
import com.example.meintasty.uicomponent.CategorySelectDropDownMenu
import com.example.meintasty.uicomponent.CustomOutlinedTextField
import com.example.meintasty.uicomponent.HeaderComponent
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantCreateMenuScreen(
    navController: NavController,
    restaurantCreateMenuViewModel: RestaurantCreateMenuViewModel = hiltViewModel()
) {

    val createMenuState = restaurantCreateMenuViewModel.createMenuState.collectAsState()
    val restaurantcreateMenuState = restaurantCreateMenuViewModel.restaurantcreateMenuState.collectAsState()

    val context = LocalContext.current
    val sharedPreferences =
        context.getSharedPreferences(Constants.SHARED_RESTAURANT_ID, Context.MODE_PRIVATE)
    val restaurantId = sharedPreferences.getInt(Constants.SHARED_RESTAURANT_ID, 0)

    var menuName = remember { mutableStateOf("") }
    var menuPrice = remember { mutableStateOf("") }
    var menuContent = remember { mutableStateOf("") }
    var currency = remember { mutableStateOf("CHF") }
    var categorySelect by remember { mutableStateOf("") }

    LaunchedEffect(restaurantId) {
        restaurantId?.let {
            restaurantCreateMenuViewModel.getDetailRestaurant(DetailRestaurantRequest(it))
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
                // Text Fields for Menu
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

                restaurantcreateMenuState.value.data?.menuList?.let { menuList ->
                    val categoryGroupList = menuList.filterNotNull().distinctBy { it.categoryId }
                    CategorySelectDropDownMenu(
                        categoryList = categoryGroupList,
                        categorySelect = categorySelect,
                        onCategoryChange = {
                            categorySelect = it
                        }
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = {
                        if (menuName != null && menuContent != null && menuPrice != null && categorySelect != null) {
                            val categoryId = restaurantcreateMenuState.value.data?.menuList
                                ?.filterNotNull()
                                ?.find { it.categoryName == categorySelect } // Seçilen kategoriyi bul
                                ?.categoryId // O kategorinin ID'sini al

                            if (categoryId != null){
                                val createMenuRequest = CreateMenuRequest(
                                    categoryId = categoryId,
                                    currency = currency.value,
                                    menuContent = menuContent.value,
                                    menuName = menuName.value,
                                    menuPic = null,
                                    menuPrice = menuPrice.value
                                    )
                                restaurantCreateMenuViewModel.createMenu(createMenuRequest)
                                Toast.makeText(context,"SUCCESS",Toast.LENGTH_SHORT).show()
                            }
                          }else {
                              Toast.makeText(context,"Is not categoryId",Toast.LENGTH_SHORT).show()
                        }
                    },
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
