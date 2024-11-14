package com.example.meintasty.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.meintasty.domain.model.UserAccountModel
import com.example.meintasty.domain.model.UserLocationModel
import com.example.meintasty.domain.model.add_basket_model.db_model.AddBasketDataModel

@Dao
interface LoginDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToken(userAccountModel: UserAccountModel)

    @Query("SELECT * FROM useraccountmodel")
    suspend fun getToken(): UserAccountModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCanton(userLocationModel: UserLocationModel)

    @Query("SELECT * FROM userlocationmodel")
    suspend fun getLocationInfo(): UserLocationModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBasket(addBasketDataModel: AddBasketDataModel)

    @Query("SELECT * FROM addbasketdatamodel")
    suspend fun allBasket(): List<AddBasketDataModel>

    @Query("UPDATE addbasketdatamodel SET quantity = :newQuantity WHERE menuId = :menuId")
    suspend fun updateQuantity(menuId: Int, newQuantity: Int)
}