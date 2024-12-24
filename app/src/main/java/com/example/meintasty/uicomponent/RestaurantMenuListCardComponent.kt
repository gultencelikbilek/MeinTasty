package com.example.meintasty.uicomponent

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.navArgument
import com.example.meintasty.R
import com.example.meintasty.domain.model.user_model_.restaurant_detail.restaurant_detail_request.DetailRestaurantRequest
import com.example.meintasty.domain.model.user_model_.restaurant_detail.restaurant_detail_response.Menu
import com.example.meintasty.feature.restaurant_feature.restaurant_profile_screen.RestaurantProfileViewModel
import com.example.meintasty.navigation.Screen

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SharedTransitionScope.RestaurantMenuListCardComponent(
    animatedVisibilityScope: AnimatedVisibilityScope,
    menu: Menu?,
    navController: NavController
) {

    val customFontFamily = FontFamily(
        Font(resId = R.font.poppins_light, weight = FontWeight.Bold)
    )
    Card(
        modifier = Modifier
            .width(85.dp)
            .height(170.dp)
            .padding(4.dp)
            .clickable {
                navController.navigate(Screen.UpdateRemoveMenuScreen.route +
                        "?id=${menu?.id}&categoryId=${menu?.categoryId}&menuName=${menu?.menuName}&menuContent=${menu?.menuContent}&menuPrice=${menu?.menuPrice}&currency=${menu?.currency}")
            },
        elevation = CardDefaults.cardElevation(1.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier.height(150.dp)
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

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = menu?.menuName.toString(), style = TextStyle(
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        color = Color.Black,
                        fontFamily = customFontFamily
                    ), modifier = Modifier.padding(bottom = 2.dp)
                )
                Text(
                    text = menu?.menuPrice.toString(), style = TextStyle(
                        fontSize = MaterialTheme.typography.titleMedium.fontSize, color = Color.Gray
                    ), modifier = Modifier.padding(bottom = 2.dp)
                )
                Text(
                    text = menu?.menuPic.toString(), style = TextStyle(
                        fontSize = MaterialTheme.typography.titleMedium.fontSize, color = Color.Gray
                    )
                )
            }

        }
    }
}