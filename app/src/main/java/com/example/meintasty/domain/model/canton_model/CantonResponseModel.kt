package com.example.meintasty.domain.model.canton_model


data class CantonResponseModel(
    val errorMessage: Any,
    val infoMessage: String,
    val success: Boolean,
    val value: List<Canton>
)