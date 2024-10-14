package com.example.meintasty.domain.model.category_model.category_response

data class CategoryResponse(
    val errorMessage: Any?,
    val infoMessage: String?,
    val success: Boolean?,
    val value: List<Category?>?
)