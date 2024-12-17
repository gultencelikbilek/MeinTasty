package com.example.meintasty.feature.user_feature.category_detail_screen

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.meintasty.R
import com.example.meintasty.domain.model.category_detail_model.category_detail_request.CategoryDetailRequest
import com.example.meintasty.uicomponent.BackIcon
import com.example.meintasty.uicomponent.CategoryDetailComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDetailScreen(
    categoryId: Int?,
    categoryName: String?,
    navController: NavController,
    categoryDetailViewModel: CategoryDetailViewModel = hiltViewModel()
) {

    val categoryDetailState = categoryDetailViewModel.categoryDetailState.collectAsState()

    val categoryDetailRequest = CategoryDetailRequest(categoryId)
    LaunchedEffect(Unit) {
        categoryDetailViewModel.getCategoryDetail(categoryDetailRequest)
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        BackIcon {
                            navController.navigateUp()
                        }
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text = categoryName.toString(),
                            style = TextStyle(
                                color = Color.White
                            ),
                            modifier = Modifier.padding(top = 14.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.mein_tasty_color)
                )
            )
        },
        content = { paddingValues ->
            if (categoryDetailState.value.isLoading == true) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = colorResource(id = R.color.mein_tasty_color)
                    )
                }
            } else {
                categoryDetailState.value.data?.let { categoryDetailList ->
                    Log.d("categoryDetailList", "$categoryDetailList")
                    val repeatedList =
                        List(10) { categoryDetailList }.flatten() // 10 kere categoryDetailList'i tekrarlıyoruz ve düz listeye dönüştürüyoruz

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        items(repeatedList) {
                            var isVisible by remember { mutableStateOf(false) }
                            // Kartın animasyon değerleri
                            val opacity by animateFloatAsState(if (isVisible) 1f else 0f)
                            var translationY = animateFloatAsState(if (isVisible) 0f else 50f)
                            // Ekrana geldikçe animasyon başlat
                            LaunchedEffect(Unit) {
                                isVisible = true
                            }

                            CategoryDetailComponent(categoryDetail = it, modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .graphicsLayer {
                                    alpha = opacity
                                    translationY = translationY
                                })
                        }
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun DetailScreenPrew(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    //CategoryDetailScreen(navController)

}