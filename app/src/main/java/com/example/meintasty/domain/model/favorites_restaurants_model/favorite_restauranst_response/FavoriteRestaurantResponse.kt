package com.example.meintasty.domain.model.favorites_restaurants_model.favorite_restauranst_response

data class FavoriteRestaurantResponse(
    val errorMessage: Any?,
    val infoMessage: String?,
    val success: Boolean?,
    val value: List<FavoriteRestaurant?>?
)