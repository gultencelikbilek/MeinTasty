package com.example.meintasty.domain.model.db_model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserLocationModel(
    @PrimaryKey(autoGenerate = true)
    val id : Int?=0,
    val cantonName: String? ="",
    val cityName: String? = "",

    )
