package com.example.meintasty.data.repoimpl

import android.content.Context
import com.example.meintasty.data.di.AppModule
import com.example.meintasty.domain.model.canton_model.request_model.CantonRequestModel
import com.example.meintasty.domain.model.canton_model.response_model.CantonResponseModel
import com.example.meintasty.domain.model.category_detail_model.category_detail_request.CategoryDetailRequest
import com.example.meintasty.domain.model.category_detail_model.category_detail_response.CategoryDetailResponse
import com.example.meintasty.domain.model.category_model.category_request.CategoryRequest
import com.example.meintasty.domain.model.category_model.category_response.CategoryResponse
import com.example.meintasty.domain.model.login_model.login_request.LoginUserRequest
import com.example.meintasty.domain.model.login_model.login_response.LoginResponseModel
import com.example.meintasty.domain.model.restaurant_detail.restaurant_detail_request.DetailRestaurantRequest
import com.example.meintasty.domain.model.restaurant_detail.restaurant_detail_response.RestaurantDetailResponse
import com.example.meintasty.domain.model.restaurant_model.restaurant_request.RestaurantRequest
import com.example.meintasty.domain.model.restaurant_model.restaurant_response.RestaurantModelResponse
import com.example.meintasty.domain.model.signup_model.signup_request.SignupRequest
import com.example.meintasty.domain.model.signup_model.signup_response.SignupResponse
import com.example.meintasty.domain.repository.NetworkRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(@ApplicationContext context: Context) :
    NetworkRepository {

    private val apiService = AppModule.providesRetrofit(context)
    private val loginDao = AppModule.providesRoom(context)
    override suspend fun loginUser(loginUserRequest: LoginUserRequest): LoginResponseModel {
        return apiService.loginUser(loginUserRequest)
    }

    override suspend fun getCanton(cantonRequestModel: CantonRequestModel): CantonResponseModel {
        return apiService.getCanton(cantonRequestModel)
    }

    override suspend fun signUp(signupRequest: SignupRequest): SignupResponse {
        return apiService.signUp(signupRequest)
    }

    override suspend fun getRestaurant(restaurantRequest: RestaurantRequest): RestaurantModelResponse {
        return apiService.getRestaurant(restaurantRequest)
    }

    override suspend fun getDetailRestaurant(detailRestaurantRequest: DetailRestaurantRequest): RestaurantDetailResponse {
        return apiService.getDetailRestaurant(detailRestaurantRequest)
    }

    override suspend fun getCategoryList(categoryRequest: CategoryRequest): CategoryResponse {
        return apiService.getCategoryList(categoryRequest)
    }

    override suspend fun getCategoryDetail(categoryDetailRequest: CategoryDetailRequest): CategoryDetailResponse {
        return apiService.getCategoryDetail(categoryDetailRequest)
    }
}