package com.example.meintasty.uicomponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.meintasty.R
import com.example.meintasty.domain.model.category_model.Category
import com.example.meintasty.navigation.Screen

@Composable
fun CategoryCardComponent(
    navController: NavController,
    category: Category?
) {
    Card(
        modifier = Modifier
            .wrapContentHeight()
            .width(100.dp)
            .padding(start = 16.dp)
            .clickable {
                    navController.navigate(Screen.CategoryDetailScreen.route+"?categoryId=${category!!.id}?categoryName=${category.categoryName}")

            },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.food_one),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
        )
        Text(
            text = category?.categoryName.toString(),
            modifier = Modifier.padding(start = 6.dp).padding(vertical = 8.dp),
            style = TextStyle(
                color = Color.Black,
                fontSize = MaterialTheme.typography.titleSmall.fontSize
            )
        )
    }
}

@Preview
@Composable
fun CategoryCardComponentPrew() {
    //CategoryCardComponent()
}