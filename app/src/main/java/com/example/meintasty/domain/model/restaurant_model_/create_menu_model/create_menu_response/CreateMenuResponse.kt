package com.example.meintasty.domain.model.restaurant_model_.create_menu_model.create_menu_response

data class CreateMenuResponse(
    val errorMessage: String?,
    val infoMessage: Any?,
    val success: Boolean?,
    val value: CreateMenu?
)