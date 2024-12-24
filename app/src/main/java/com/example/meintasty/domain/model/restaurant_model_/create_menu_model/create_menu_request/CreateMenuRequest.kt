package com.example.meintasty.domain.model.restaurant_model_.create_menu_model.create_menu_request

data class CreateMenuRequest(
    val categoryId: Int?,
    val currency: String?,
    val menuContent: String?,
    val menuName: String?,
    val menuPic: String?,
    val menuPrice: String?,
)