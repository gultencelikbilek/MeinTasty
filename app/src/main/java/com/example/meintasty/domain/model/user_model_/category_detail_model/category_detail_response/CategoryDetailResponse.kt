package com.example.meintasty.domain.model.user_model_.category_detail_model.category_detail_response

data class CategoryDetailResponse(
    val errorMessage: Any?,
    val infoMessage: String?,
    val success: Boolean?,
    val value: List<CategoryDetail?>?
)