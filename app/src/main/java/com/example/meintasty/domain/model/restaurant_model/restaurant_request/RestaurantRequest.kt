package com.example.meintasty.domain.model.restaurant_model.restaurant_request

data class RestaurantRequest(
    val categoryId: Int?,
    val cityCode: Int?,
    val pageNumber: Int? = 1
)