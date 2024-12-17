package com.example.meintasty.domain.model.create_menu_model.create_menu_response

data class CreateMenuResponse(
    val errorMessage: String?,
    val infoMessage: Any?,
    val success: Boolean?,
    val value: CreateMenu?
)