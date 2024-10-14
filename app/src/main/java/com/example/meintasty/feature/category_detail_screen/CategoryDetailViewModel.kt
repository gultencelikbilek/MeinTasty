package com.example.meintasty.feature.category_detail_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meintasty.data.repoimpl.CategoryDetailRepositoryImpl
import com.example.meintasty.domain.model.category_detail_model.Category
import com.example.meintasty.domain.model.category_detail_model.CategoryDetail
import com.example.meintasty.domain.model.category_detail_model.CategoryDetailRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryDetailViewModel @Inject constructor(
    private val categoryDetailRepositoryImpl: CategoryDetailRepositoryImpl
):ViewModel(){

    private val _categoryDetailState = MutableStateFlow(CategoryDetailState())
    val categoryDetailState = _categoryDetailState.asStateFlow()

    private val _categoryState = MutableStateFlow(CategoryState())
    val categoryState = _categoryState.asStateFlow()

    fun getCategoryDetail(categoryDetailRequest: CategoryDetailRequest){
        viewModelScope.launch {
            val response = categoryDetailRepositoryImpl.getCategoryDetail(categoryDetailRequest)
            _categoryDetailState.value = CategoryDetailState(
                data = response.value,
                success = response.success,
                error = response.errorMessage.toString(),
                isLoading = true
            )
        }
    }
}

data class CategoryDetailState(
    val data : List<CategoryDetail?>? = null,
    val success : Boolean?= false,
    val error : String? = "",
    val isLoading : Boolean? = false
)

data class CategoryState(
    val data : List<Category?>? = null
)