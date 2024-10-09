package com.example.meintasty.domain.model

data class DetailRestaurant(
    val addressList: List<Address>,
    val email: String,
    val menuList: List<Any>,
    val orderList: List<Any>,
    val phoneNumber: String,
    val restaurantName: String,
    val taxNumber: String,
    val workDayFrom: String,
    val workDayTo: String,
    val workHourFrom: String,
    val workHourTo: String
)