package com.example.meintasty.feature.category_detail_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meintasty.data.usecase.CategoryDetailUseCase
import com.example.meintasty.domain.model.category_detail_model.category_detail_response.Category
import com.example.meintasty.domain.model.category_detail_model.category_detail_response.CategoryDetail
import com.example.meintasty.domain.model.category_detail_model.category_detail_request.CategoryDetailRequest
import com.example.meintasty.feature.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryDetailViewModel @Inject constructor(
    private val categoryDetailUseCase: CategoryDetailUseCase
) : ViewModel() {

    private val _categoryDetailState = MutableStateFlow(CategoryDetailState())
    val categoryDetailState = _categoryDetailState.asStateFlow()

    private val _categoryState = MutableStateFlow(CategoryState())
    val categoryState = _categoryState.asStateFlow()

    fun getCategoryDetail(categoryDetailRequest: CategoryDetailRequest) {
        viewModelScope.launch {
            categoryDetailUseCase.invoke(categoryDetailRequest).collect { result ->
                when (result) {
                    is NetworkResult.Failure -> {
                        _categoryDetailState.value = CategoryDetailState(
                            data = null,
                            success = false,
                            error = result.msg,
                            isLoading = true
                        )
                    }

                    NetworkResult.Loading -> {
                        _categoryDetailState.value = CategoryDetailState(
                            data = null,
                            success = false,
                            error = "",
                            isLoading = true
                        )
                    }

                    is NetworkResult.Success -> {
                        _categoryDetailState.value = CategoryDetailState(
                            data = result.data.value,
                            success = true,
                            error = "",
                            isLoading = false
                        )
                    }
                }
            }
        }
    }
}

data class CategoryDetailState(
    val data: List<CategoryDetail?>? = null,
    val success: Boolean? = false,
    val error: String? = "",
    val isLoading: Boolean? = false
)

data class CategoryState(
    val data: List<Category?>? = null
)