package com.example.meintasty.domain.model.restaurant_model_.restaurant_login_model.restaurant_login_response

data class RestaurantLoginModel(
    val fullName: String,
    val restaurantId: Int,
    val roleList: List<String>,
    val token: String
)