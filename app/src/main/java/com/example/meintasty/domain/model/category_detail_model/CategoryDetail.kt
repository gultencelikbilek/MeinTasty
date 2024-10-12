package com.example.meintasty.domain.model.category_detail_model

data class CategoryDetail(
    val categories: List<Category?>?,
    val currencyCode: String?,
    val delivery: String?,
    val id: Int?,
    val minBudget: String?,
    val minTime: Int?,
    val restaurantName: String?,
    val starCount: Int?
)