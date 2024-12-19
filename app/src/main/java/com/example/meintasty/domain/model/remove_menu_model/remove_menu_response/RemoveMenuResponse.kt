package com.example.meintasty.domain.model.remove_menu_model.remove_menu_response

data class RemoveMenuResponse(
    val errorMessage: String?,
    val infoMessage: Any?,
    val success: Boolean?,
    val value: RemoveMenu?
)