package com.example.meintasty.domain.model.restaurant_detail.restaurant_detail_response

data class RestaurantDetailResponse(
    val errorMessage: String?,
    val infoMessage: String?,
    val success: Boolean?,
    val value: DetailRestaurant?
)