package com.example.meintasty.feature.category_detail_screen

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.meintasty.R
import com.example.meintasty.domain.model.category_detail_model.CategoryDetailRequest
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

    if (categoryDetailState.value.isLoading == false){
        CircularProgressIndicator()
    }else {
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
                categoryDetailState.value.data?.let { categoryDetailList ->
                    val repeatedList =
                        List(10) { categoryDetailList }.flatten() // 10 kere categoryDetailList'i tekrarlıyoruz ve düz listeye dönüştürüyoruz

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        items(repeatedList) {
                            CategoryDetailComponent(categoryDetail = it)
                        }
                    }
                }
            }
        )
    }
}

@Preview
@Composable
fun DetailScreenPrew(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    //CategoryDetailScreen(navController)

}