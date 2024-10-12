package com.example.meintasty.feature.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.meintasty.R
import com.example.meintasty.domain.model.category_detail_model.CategoryDetail

@Composable
fun CategoryDetailComponent(categoryDetail: CategoryDetail?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .padding(vertical = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Row() {
            Box(
                modifier = Modifier
                    .border(
                        1.dp,
                        Color.Transparent,
                        shape = RoundedCornerShape(25.dp)
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.food_one),
                    contentDescription = "",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(10.dp)
                )
            }
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = categoryDetail?.restaurantName.toString(),
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        color = Color.Black
                    )
                )
            }
        }
    }
}
