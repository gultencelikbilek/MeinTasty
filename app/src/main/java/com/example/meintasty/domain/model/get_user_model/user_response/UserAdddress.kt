package com.example.meintasty.domain.model.get_user_model.user_response

data class UserAdddress(
    val addressName: String?,
    val addressText: String?,
    val cantonName: String?,
    val cityCode: Int?,
    val cityName: String?,
    val id: Int?,
    val isDefault: Boolean?,
    val street: String?,
    val userId: Int?,
    val zipCode: String?
)