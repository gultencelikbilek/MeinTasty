package com.example.meintasty.domain.model.restaurant_detail

data class DetailRestaurant(
    val addressList: List<Address?>?,
    val email: String?,
    val menuList: List<Menu?>?,
    val orderList: List<Order?>?,
    val phoneNumber: String?,
    val restaurantName: String?,
    val taxNumber: String?,
    val workDayFrom: String?,
    val workDayTo: String?,
    val workHourFrom: String?,
    val workHourTo: String?
)