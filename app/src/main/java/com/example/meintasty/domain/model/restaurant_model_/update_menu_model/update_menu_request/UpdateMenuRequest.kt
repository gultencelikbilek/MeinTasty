package com.example.meintasty.domain.model.restaurant_model_.update_menu_model.update_menu_request

data class UpdateMenuRequest(
    val categoryId: Int?,
    val currency: String?,
    val id: Int?,
    val menuContent: String?,
    val menuName: String?,
    val menuPic: String?,
    val menuPrice: String?
)