package com.example.meintasty.uicomponent

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.meintasty.R
import com.example.meintasty.domain.model.restaurant_detail.Menu

@Composable
fun MenuListCardComponent(menu: Menu?) {

    val customFontFamily = FontFamily(
        Font(resId = R.font.poppins_light, weight = FontWeight.Bold)
    )

    var count by remember {
        mutableStateOf(0)
    }

    val context = LocalContext.current

    Card(
        modifier = Modifier
            .width(85.dp)
            .height(170.dp)
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(1.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .height(150.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.restaurant_bg),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .border(1.dp, Color.Transparent, RoundedCornerShape(25.dp))
            )
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, end = 4.dp),

            ) {
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .background(colorResource(id = R.color.white), RoundedCornerShape(12.dp))
                        .clickable {
                            count++
                            Toast.makeText(context,"Count:$count",Toast.LENGTH_SHORT).show()
                        }
                        .border(1.dp, Color.LightGray, RoundedCornerShape(25.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.plus),
                        contentDescription = stringResource(id = R.string.add),
                        tint = Color.Black,
                        modifier = Modifier.size(10.dp)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = menu?.menuName.toString(),
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        color = Color.Black,
                        fontFamily = customFontFamily
                    ),
                    modifier = Modifier.padding(bottom = 2.dp) // Space between texts
                )
                Text(
                    text = menu?.price.toString(),
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        color = Color.Gray
                    ),
                    modifier = Modifier.padding(bottom = 2.dp)
                )
                Text(
                    text = menu?.menuPic.toString(),
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        color = Color.Gray
                    )
                )
            }
        }
    }
}