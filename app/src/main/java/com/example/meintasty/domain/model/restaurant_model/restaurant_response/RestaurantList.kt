package com.example.meintasty.domain.model.restaurant_model.restaurant_response

data class RestaurantList(
    val currentPage: Int?,
    val nextPage: Int?,
    val prevPage: Int?,
    val restaurants: List<Restaurant?>?,
    val totalCount: Int?,
    val totalPages: Int?
)