package com.example.meintasty.domain.model.tax_model.tax_response

data class TaxResponse(
    val errorMessage: Any?,
    val infoMessage: String?,
    val success: Boolean?,
    val value: List<Tax?>?
)