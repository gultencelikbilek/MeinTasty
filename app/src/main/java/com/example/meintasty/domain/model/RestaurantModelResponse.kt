package com.example.meintasty.domain.model

data class RestaurantModelResponse(
    val errorMessage: Any,
    val infoMessage: String,
    val success: Boolean,
    val value: List<Restaurant>
)