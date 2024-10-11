package com.example.meintasty.domain.model.restaurant_detail

data class RestaurantDetailResponse(
    val errorMessage: String?,
    val infoMessage: String?,
    val success: Boolean?,
    val value: DetailRestaurant?
)