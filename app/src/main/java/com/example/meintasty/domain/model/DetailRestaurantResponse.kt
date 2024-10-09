package com.example.meintasty.domain.model

data class DetailRestaurantResponse(
    val errorMessage: Any,
    val infoMessage: String,
    val success: Boolean,
    val value: DetailRestaurant
)