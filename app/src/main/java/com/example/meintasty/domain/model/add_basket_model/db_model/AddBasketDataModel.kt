package com.example.meintasty.domain.model.add_basket_model.db_model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AddBasketDataModel(
    @PrimaryKey(autoGenerate = false)
    val id: Int? = 0,
    val basketDate: String?,
    val currencyCode: String?,
    val menuId: Int?,
    val price: String?,
    val quantity: Int?,
    val restaurantId: Int?,
    val userId: Int?
)