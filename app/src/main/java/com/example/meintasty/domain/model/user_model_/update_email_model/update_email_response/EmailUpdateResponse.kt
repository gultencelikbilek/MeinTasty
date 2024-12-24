package com.example.meintasty.domain.model.user_model_.update_email_model.update_email_response

data class EmailUpdateResponse(
    val errorMessage: Any?,
    val infoMessage: String?,
    val success: Boolean?,
    val value: Value?
)