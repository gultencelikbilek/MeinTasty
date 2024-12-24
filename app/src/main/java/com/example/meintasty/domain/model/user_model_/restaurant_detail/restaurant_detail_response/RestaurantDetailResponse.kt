package com.example.meintasty.domain.model.user_model_.restaurant_detail.restaurant_detail_response

data class RestaurantDetailResponse(
    val errorMessage: Any?,
    val infoMessage: String?,
    val success: Boolean?,
    val value: DetailRestaurant?
)