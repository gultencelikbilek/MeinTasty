package com.example.meintasty.domain.model.db_model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RestaurantAccountModel(
    @PrimaryKey(autoGenerate = true)
    val id : Int? = 0,
    val restaurantId : Int? = 0,
    val fullName: String? ="",
    val roleList : List<String?>? = emptyList(),
    val token: String? ="",
    val isRestaurant : Boolean? = false
)