package com.example.meintasty.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.meintasty.domain.Converters
import com.example.meintasty.domain.model.db_model.RestaurantAccountModel
import com.example.meintasty.domain.model.db_model.UserAccountModel
import com.example.meintasty.domain.model.db_model.UserLocationModel


@Database(entities = [UserAccountModel::class, UserLocationModel::class, RestaurantAccountModel::class], version = 1)
@TypeConverters(Converters::class)
abstract class LoginDatabase :RoomDatabase(){
    abstract fun loginDao() : LoginDao
}