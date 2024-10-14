package com.example.meintasty.domain.model.canton_model.response_model

import com.example.meintasty.domain.model.canton_model.response_model.Canton


data class CantonResponseModel(
    val errorMessage: Any,
    val infoMessage: String,
    val success: Boolean,
    val value: List<Canton>
)