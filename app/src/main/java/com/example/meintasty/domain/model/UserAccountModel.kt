package com.example.meintasty.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserAccountModel(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val fullName: String? ="",
    val roleList : List<String>,
    val token: String? =""
)
