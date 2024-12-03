package com.example.meintasty.domain.model.restaurant_detail.restaurant_detail_response

data class Menu(
    val categoryId: Int?,
    val categoryName: String?,
    val currency: String?,
    val menuContent: String?,
    val menuName: String?,
    val menuPic: String?,
    val menuPrice: String?,
    val restaurantId: Int?
)