package com.example.meintasty.domain.model

data class Address(
    val addressId: Int,
    val addressName: String,
    val addressText: String,
    val cityCode: Int,
    val restaurantId: Int,
    val street: String
)