package com.example.meintasty.data.network

import com.example.meintasty.data.Constants
import com.example.meintasty.domain.model.canton_model.request_model.CantonRequestModel
import com.example.meintasty.domain.model.canton_model.response_model.CantonResponseModel
import com.example.meintasty.domain.model.category_detail_model.category_detail_request.CategoryDetailRequest
import com.example.meintasty.domain.model.category_detail_model.category_detail_response.CategoryDetailResponse
import com.example.meintasty.domain.model.category_model.category_request.CategoryRequest
import com.example.meintasty.domain.model.category_model.category_response.CategoryResponse
import com.example.meintasty.domain.model.restaurant_detail.restaurant_detail_request.DetailRestaurantRequest
import com.example.meintasty.domain.model.login_model.login_response.LoginResponseModel
import com.example.meintasty.domain.model.login_model.login_request.LoginUserRequest
import com.example.meintasty.domain.model.restaurant_model.restaurant_response.RestaurantModelResponse
import com.example.meintasty.domain.model.restaurant_model.restaurant_request.RestaurantRequest
import com.example.meintasty.domain.model.restaurant_detail.restaurant_detail_response.RestaurantDetailResponse
import com.example.meintasty.domain.model.signup_model.signup_request.SignupRequest
import com.example.meintasty.domain.model.signup_model.signup_response.SignupResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST(Constants.END_POINT_LOGIN)
    suspend fun loginUser(@Body loginUserRequest: LoginUserRequest) : LoginResponseModel

    @POST(Constants.END_POINT_SIGNUP)
    suspend fun signUp(@Body signupRequest: SignupRequest) : SignupResponse

    @POST(Constants.ENDPOINT_CANTON)
    suspend fun getCanton(@Body cantonRequestModel: CantonRequestModel) : CantonResponseModel

    @POST(Constants.END_POINT_RESTAURANT)
    suspend fun getRestaurant(@Body restaurantRequest: RestaurantRequest) : RestaurantModelResponse

    @POST(Constants.END_POINT_DETAIL_RESTAURANT)
    //suspend fun getDetailRestaurant(@Body detailRestaurantRequest: DetailRestaurantRequest) : NetworkResult<RestaurantDetailResponse>
    suspend fun getDetailRestaurant(@Body detailRestaurantRequest: DetailRestaurantRequest) : RestaurantDetailResponse

    @POST(Constants.END_POINT_CATEGORY)
    suspend fun getCategoryList(@Body categoryRequest: CategoryRequest) : CategoryResponse

    @POST(Constants.END_POINT_CATEGORY_DETAIL)
    suspend fun getCategoryDetail(@Body categoryDetailRequest: CategoryDetailRequest) : CategoryDetailResponse

}