package com.example.meintasty.feature.restaurant_feature.update_remove_menu_screen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.meintasty.R
import com.example.meintasty.data.Constants
import com.example.meintasty.domain.model.restaurant_model_.create_menu_model.create_menu_request.CreateMenuRequest
import com.example.meintasty.domain.model.restaurant_model_.remove_menu_model.remove_menu_request.RemoveMenuRequest
import com.example.meintasty.domain.model.user_model_.restaurant_detail.restaurant_detail_request.DetailRestaurantRequest
import com.example.meintasty.domain.model.restaurant_model_.update_menu_model.update_menu_request.UpdateMenuRequest
import com.example.meintasty.navigation.Screen
import com.example.meintasty.uicomponent.BackIcon
import com.example.meintasty.uicomponent.CategorySelectDropDownMenu
import com.example.meintasty.uicomponent.CustomOutlinedTextField
import com.example.meintasty.uicomponent.HeaderComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateRemoveMenuScreen(
    navController: NavController,
    id: Int?,
    categoryId: Int?,
    menuName: String?,
    menuContent: String?,
    menuPrice: String?,
    currency: String?,
    updateRemoveMenuViewModel: UpdateRemoveMenuViewModel = hiltViewModel()
) {
    val menuNameState = remember { mutableStateOf(menuName ?: "") }
    val menuContentState = remember { mutableStateOf(menuContent ?: "") }
    val menuPriceState = remember { mutableStateOf(menuPrice ?: "") }
    val currencyState = remember { mutableStateOf(currency ?: "") }
    val categoryIdState = remember { mutableStateOf(categoryId) }
    val categorySelectState = remember { mutableStateOf("") }

    val context = LocalContext.current
    val sharedPreferences =
        context.getSharedPreferences(Constants.SHARED_RESTAURANT_ID, Context.MODE_PRIVATE)
    val restaurantId = sharedPreferences.getInt(Constants.SHARED_RESTAURANT_ID, 0)
    val updateMenuState = updateRemoveMenuViewModel.updateMenuState.collectAsState()
    val restaurantUpdateMenuState =
        updateRemoveMenuViewModel.restaurantMenuUpdateState.collectAsState()

    LaunchedEffect(restaurantId) {
        restaurantId.let {
            updateRemoveMenuViewModel.getDetailRestaurant(DetailRestaurantRequest(it))
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
                        HeaderComponent(text = stringResource(id = R.string.update_menu))
                    }
                },
                actions = {
                    IconButton(onClick = {
                        updateRemoveMenuViewModel.removeMenu(RemoveMenuRequest(id))
                        Toast.makeText(context,"Delete menu",Toast.LENGTH_SHORT).show()
                        navController.navigate(Screen.RestaurantMenuDetailScreen.route)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.delete),
                            contentDescription = "",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.mein_tasty_color)
                )
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier.padding(paddingValues)
            ) {
                // Menu Name TextField
                CustomOutlinedTextField(
                    value = menuNameState.value,
                    onValueChange = { menuNameState.value = it },
                    placeholder = stringResource(id = R.string.menu_name)
                )

                CustomOutlinedTextField(
                    value = menuContentState.value,
                    onValueChange = { menuContentState.value = it },
                    placeholder = stringResource(id = R.string.menu_content)
                )

                CustomOutlinedTextField(
                    value = menuPriceState.value,
                    onValueChange = { menuPriceState.value = it },
                    placeholder = stringResource(id = R.string.menu_price)
                )

                CustomOutlinedTextField(
                    value = currencyState.value,
                    onValueChange = { currencyState.value = it },
                    placeholder = stringResource(id = R.string.currency)
                )
                // Kategori Dropdown
                restaurantUpdateMenuState.value.data?.menuList?.let { menuList ->
                    val categoryGroupList = menuList.filterNotNull().distinctBy { it.categoryId }

                    // Başlangıçta categoryId'ye uygun adı bul ve state'e ata
                    LaunchedEffect(categoryId) {
                        categoryGroupList.firstOrNull { it.categoryId == categoryId }?.let {
                            categorySelectState.value = it.categoryName.toString()
                        }
                    }

                    CategorySelectDropDownMenu(
                        categoryList = categoryGroupList,
                        categorySelect = categorySelectState.value,
                        onCategoryChange = { selectedCategoryName ->
                            categorySelectState.value = selectedCategoryName
                            categoryIdState.value =
                                categoryGroupList.find { it.categoryName == selectedCategoryName }?.categoryId
                        }
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = {
                        if (id != null) {
                            val updateMenuRequest = UpdateMenuRequest(
                                categoryId = categoryIdState.value,
                                currency = currencyState.value,
                                id = id,
                                menuContent = menuContentState.value,
                                menuName = menuNameState.value,
                                menuPic = null,
                                menuPrice = menuPriceState.value,
                            )
                            Log.d("categoryId", "$id")
                            Log.d("categoryId", "${categoryIdState.value}")
                            Log.d("categoryId", "$currency")
                            Log.d("categoryId", "$menuContent")
                            Log.d("categoryId", "$menuName")
                            updateRemoveMenuViewModel.updateMenu(updateMenuRequest)
                            navController.navigate(Screen.RestaurantMenuDetailScreen.route)
                            Toast.makeText(context, "UPDATE MENU", Toast.LENGTH_SHORT).show()

                        } else {
                            Toast.makeText(context, "Is not update menu", Toast.LENGTH_SHORT).show()
                        }

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.mein_tasty_color)
                    )
                ) {
                    Text(text = "UPDATE MENU")
                }
            }
        }
    )
}
