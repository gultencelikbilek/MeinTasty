package com.example.meintasty.domain.model.restaurant_model_.restaurant_login_model.restaurant_login_response

data class RestaurantLoginResponse(
    val errorMessage: Any?,
    val infoMessage: Any?,
    val success: Boolean?,
    val value: RestaurantLoginModel?
)