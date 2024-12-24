package com.example.meintasty.domain.model.db_model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserAccountModel(
    @PrimaryKey(autoGenerate = true)
    val id : Int? = 0,
    val userId : Int?=0,
    val fullName: String? ="",
    val roleList : List<String?>? = emptyList(),
    val token: String? ="",
    val isUser : Boolean? = false
)
