package com.example.meintasty.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.meintasty.domain.model.UserAccountModel

@Dao
interface LoginDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToken(userAccountModel: UserAccountModel)

}