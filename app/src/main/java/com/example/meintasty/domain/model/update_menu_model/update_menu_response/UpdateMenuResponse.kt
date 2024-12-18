package com.example.meintasty.domain.model.update_menu_model.update_menu_response

data class UpdateMenuResponse(
    val errorMessage: Any?,
    val infoMessage: String?,
    val success: Boolean?,
    val value: Value?
)