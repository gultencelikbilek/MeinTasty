package com.example.meintasty.domain.model.tax_model.tax_response

data class Tax(
    val amount: String?,
    val currency: String?,
    val description: String?,
    val id: Int?,
    val name: String?
)