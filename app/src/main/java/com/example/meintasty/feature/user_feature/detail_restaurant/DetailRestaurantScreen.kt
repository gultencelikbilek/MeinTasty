@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.example.meintasty.feature.user_feature.detail_restaurant

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.meintasty.R
import com.example.meintasty.domain.model.get_basket_model.get_basket_request.GetBasketRequest
import com.example.meintasty.domain.model.restaurant_detail.restaurant_detail_request.DetailRestaurantRequest
import com.example.meintasty.uicomponent.BackIcon
import com.example.meintasty.uicomponent.MenuListCardComponent

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SharedTransitionScope.DetailRestaurantScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    restaurantId: Int?,
    navController: NavController,
    detailRestaurantViewModel: DetailRestaurantViewModel = hiltViewModel()
) {

    val detailRestState = detailRestaurantViewModel.detailRestState.collectAsState().value
    val basketRestIdControlState =
        detailRestaurantViewModel.basketRestIdControlState.collectAsState()
    val basketControlState = basketRestIdControlState.value.data
    Log.d("basjetControlState:", "${basketControlState?.firstOrNull()?.restaurantId}")
    val detailRestaurantRequest = DetailRestaurantRequest(restaurantId!!.toInt())
    val customFontFamily = FontFamily(
        Font(resId = R.font.poppins_bold, weight = FontWeight.Normal)
    )
    val userModelState = detailRestaurantViewModel.userModelState.collectAsState().value

    val gridState =
        rememberLazyListState() // Grid'in state'ini yönetmek için, rememberLazyGridState() kaydırma pozisyonunu kontrol etmenizi sağlar.

    val selectedCategoryId = remember { mutableStateOf<Int?>(null) }

    LaunchedEffect(Unit) {
        detailRestaurantViewModel.getDetailRestaurant(detailRestaurantRequest)
    }
    userModelState.data.let {
        val basketRequest = GetBasketRequest(restaurantId = restaurantId, userId = it?.userId)
        detailRestaurantViewModel.getBasket(basketRequest)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        BackIcon {
                            navController.navigateUp()
                        }
                        detailRestState.data?.restaurantName?.let { it1 ->
                            Text(
                                text = it1,
                                modifier = Modifier
                                    .padding(start = 4.dp)
                                    .padding(top = 6.dp),
                                color = Color.White,
                                fontFamily = customFontFamily
                            )
                        }
                    }
                }, colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.mein_tasty_color)
                )
            )
        }, content = { paddingValues ->
            if (detailRestState.isLoading == true) {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = colorResource(id = R.color.mein_tasty_color)
                    )
                }
            } else if (detailRestState.isSuccess == true) {
                Log.d("succes:", "${detailRestState.data}")
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.food_one),
                            contentDescription = "",
                            modifier = Modifier
                                .size(180.dp)
                                .sharedBounds(
                                    rememberSharedContentState(key = "image/${R.drawable.restaurant_bg}"),
                                    animatedVisibilityScope = animatedVisibilityScope,
                                    enter = fadeIn(),
                                    exit = fadeOut(),
                                    resizeMode = SharedTransitionScope.ResizeMode.ScaleToBounds()
                                ),
                            contentScale = ContentScale.Crop
                        )
                    }

                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        detailRestState.data?.menuList?.let { menuList ->
                            val distinctCategories =
                                menuList.filterNotNull().distinctBy { it.categoryId }

                            items(distinctCategories) { category ->
                                val isSelected =
                                    selectedCategoryId.value == category.categoryId // Şu anki kategori seçili mi?
                                Box(
                                    modifier = Modifier
                                        .width(80.dp)
                                        .height(40.dp)
                                        .padding(horizontal = 8.dp, vertical = 6.dp)
                                        .clip(RoundedCornerShape(25.dp)) // Dış şekli kliple
                                        .background(
                                            color = if (isSelected) colorResource(id = R.color.mein_tasty_color) else colorResource(
                                                id = R.color.white
                                            )
                                        )
                                        .clickable {
                                            selectedCategoryId.value =
                                                category.categoryId // Seçili kategoriyi güncelle
                                        }
                                ) {
                                    Text(
                                        text = category.categoryName.orEmpty(),
                                        style = TextStyle(
                                            fontSize = MaterialTheme.typography.titleSmall.fontSize,
                                            color = if (isSelected) colorResource(id = R.color.white) else colorResource(
                                                id = R.color.mein_tasty_color
                                            )
                                        ),
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                }
                            }
                        }
                    }

                    detailRestState.data?.menuList?.let { menuList ->
                        val filteredMenu = if (selectedCategoryId.value == null) {
                            menuList
                        } else {
                            menuList.filter { it?.categoryId == selectedCategoryId.value }
                        }
                        // Liste her değiştiğinde başa dön
                        LaunchedEffect(filteredMenu) {
                            gridState.scrollToItem(0) //gridState.scrollToItem(0) ile liste başına kaydırılır.
                        }
                        LazyColumn(
                            state = gridState,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                                .background(Color.White)
                        ) {
                            items(filteredMenu) { menu ->
                                MenuListCardComponent(
                                    animatedVisibilityScope,
                                    menu = menu,
                                    basketControlState,
                                    restaurantId,
                                    detailRestaurantViewModel = detailRestaurantViewModel,
                                )
                            }
                        }
                    }
                }
            } else {
                Log.d("detailRestaurant:error", "${detailRestState.isError}")
            }
        })
}