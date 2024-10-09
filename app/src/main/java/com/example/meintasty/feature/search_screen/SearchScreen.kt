package com.example.meintasty.feature.search_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.meintasty.R
import com.example.meintasty.domain.model.foodList
import com.example.meintasty.feature.component.FoodCardComponent
import com.example.meintasty.feature.component.NearbyRestaurantCardComponent
import com.example.meintasty.feature.component.PopulerRestaurantCardComponent
import com.example.meintasty.feature.component.SearchComponent
import com.example.meintasty.feature.component.SearchHeaderComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen() {
    var query = remember {
        mutableStateOf("")
    }
    val customFontFamily = FontFamily(
        Font(resId = R.font.poppins_italic, weight = FontWeight.Normal)
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.mein_tasty),
                        style = TextStyle(
                            color = Color.White,
                            fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                            fontFamily =  customFontFamily
                        )
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.mein_tasty_color)
                )
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding)
                    .background(colorResource(id = R.color.white))
                    .wrapContentHeight()
                    .verticalScroll(rememberScrollState())
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colorResource(id = R.color.mein_tasty_color))
                        .height(70.dp)
                ) {
                   SearchComponent(
                       query = query.value,
                       onQueryChange ={}
                   )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(top = 6.dp)
                ) {
                   SearchHeaderComponent(text = stringResource(id = R.string.compains))
                    LazyRow(
                        modifier = Modifier
                            .padding(top = 30.dp)
                            .background(Color.White)
                    ) {
                        foodList.let { listFood ->
                            items(listFood) {
                                FoodCardComponent(food = it)
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                   SearchHeaderComponent(text = stringResource(id = R.string.populer_restaurants))
                    LazyRow(
                        modifier = Modifier
                            .padding(top = 30.dp)
                            .fillMaxWidth()
                    ) {
                        foodList.let { listFood ->
                            items(listFood) {
                                PopulerRestaurantCardComponent(food = it)
                                Spacer(modifier = Modifier.width(16.dp))
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                SearchHeaderComponent(text = stringResource(id = R.string.restaurant_nearby))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)

                ) {

                    Image(
                        painter = painterResource(id = R.drawable.google_maps),
                        contentDescription = "Background Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth()
                    )
                    LazyRow(
                        modifier = Modifier
                            .padding(top = 30.dp)
                            .fillMaxWidth()
                    ) {
                        foodList.let { listFood ->
                            items(listFood) {
                                NearbyRestaurantCardComponent(food = it)
                            }
                        }
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun SearchScreenPrew() {
    SearchScreen()
}