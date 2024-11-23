package com.example.meintasty.domain.model.restaurant_model.restaurant_response

data class RestaurantModelResponse(
    val errorMessage: Any?,
    val infoMessage: String?,
    val success: Boolean?,
    val value: List<Restaurant?>?
)