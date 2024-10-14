package com.example.meintasty.domain.model.canton_model.response_model

data class Canton(
    val cantonName: String,
    val cities: List<City>,
    val id: Int
)