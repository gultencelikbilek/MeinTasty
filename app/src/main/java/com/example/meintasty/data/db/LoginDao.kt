package com.example.meintasty.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.meintasty.domain.model.db_model.RestaurantAccountModel
import com.example.meintasty.domain.model.db_model.UserAccountModel
import com.example.meintasty.domain.model.db_model.UserLocationModel

@Dao
interface LoginDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserToken(userAccountModel: UserAccountModel)

    @Query("SELECT * FROM useraccountmodel")
    suspend fun getUserToken(): UserAccountModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRestaurantToken(restaurantAccountModel: RestaurantAccountModel)

    @Query("SELECT * FROM restaurantaccountmodel")
    suspend fun getRestaurantToken() : RestaurantAccountModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCanton(userLocationModel: UserLocationModel)

    @Query("SELECT * FROM userlocationmodel")
    suspend fun getLocationInfo(): UserLocationModel
}