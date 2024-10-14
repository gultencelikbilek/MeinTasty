package com.example.meintasty.domain.model.restaurant_detail.restaurant_detail_response

data class Menu(
    val currency: String?,
    val menuId: Int?,
    val menuName: String?,
    val menuPic: String?,
    val price: Double?,
    val restaurantId: Int?
)