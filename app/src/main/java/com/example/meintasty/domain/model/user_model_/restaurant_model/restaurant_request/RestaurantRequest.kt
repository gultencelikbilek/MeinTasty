package com.example.meintasty.domain.model.user_model_.restaurant_model.restaurant_request

data class RestaurantRequest(
    val categoryIdList: List<Int?>?,
    val cityCode: Int?,
    val pageNumber: Int? = 1
)