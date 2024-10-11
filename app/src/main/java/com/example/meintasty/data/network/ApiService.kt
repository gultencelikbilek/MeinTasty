package com.example.meintasty.data.network

import com.example.meintasty.data.Constants
import com.example.meintasty.domain.model.canton_model.CantonRequestModel
import com.example.meintasty.domain.model.canton_model.CantonResponseModel
import com.example.meintasty.domain.model.restaurant_detail.DetailRestaurantRequest
import com.example.meintasty.domain.model.login_model.LoginResponseModel
import com.example.meintasty.domain.model.login_model.LoginUserRequest
import com.example.meintasty.domain.model.restaurant_model.RestaurantModelResponse
import com.example.meintasty.domain.model.restaurant_model.RestaurantRequest
import com.example.meintasty.domain.model.restaurant_detail.RestaurantDetailResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST(Constants.END_POINT_LOGIN)
    suspend fun loginUser(@Body loginUserRequest: LoginUserRequest) : LoginResponseModel

    @POST(Constants.ENDPOINT_CANTON)
    suspend fun getCanton(@Body cantonRequestModel: CantonRequestModel) : CantonResponseModel

    @POST(Constants.END_POINT_RESTAURANT)
    suspend fun getRestaurant(@Body restaurantRequest: RestaurantRequest) : RestaurantModelResponse

    @POST(Constants.END_POINT_DETAIL_RESTAURANT)
    suspend fun getDetailRestaurant(@Body detailRestaurantRequest: DetailRestaurantRequest) : RestaurantDetailResponse
}