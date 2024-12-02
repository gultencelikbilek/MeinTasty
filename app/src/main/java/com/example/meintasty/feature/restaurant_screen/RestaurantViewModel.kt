package com.example.meintasty.feature.restaurant_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meintasty.domain.usecase.CategoryUseCase
import com.example.meintasty.domain.usecase.GetLocaitonInfoUseCase
import com.example.meintasty.domain.usecase.RestaurantUseCase
import com.example.meintasty.domain.model.UserLocationModel
import com.example.meintasty.domain.model.category_model.category_response.Category
import com.example.meintasty.domain.model.category_model.category_request.CategoryRequest
import com.example.meintasty.domain.model.favorites_restaurants_model.favorite_restauranst_request.FavoritesRestaurantRequest
import com.example.meintasty.domain.model.favorites_restaurants_model.favorite_restauranst_response.FavoriteRestaurant
import com.example.meintasty.domain.model.restaurant_model.restaurant_request.RestaurantRequest
import com.example.meintasty.domain.model.restaurant_model.restaurant_response.Restaurant
import com.example.meintasty.domain.model.restaurant_model.restaurant_response.RestaurantList
import com.example.meintasty.domain.usecase.FavoriteRestaurantUseCase
import com.example.meintasty.feature.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val restaurantUseCase: RestaurantUseCase,
    private val categoryUseCase: CategoryUseCase,
    private val getLocaitonInfoUseCase: GetLocaitonInfoUseCase,
    private val favoriteRestaurantUseCase: FavoriteRestaurantUseCase
) : ViewModel() {

    private val _restaurantState = MutableStateFlow(RestaurantState())
    val restaurantState = _restaurantState.asStateFlow()

    private val _locationState = MutableStateFlow(LocationState())
    val locationState = _locationState.asStateFlow()

    private val _categoryState = MutableStateFlow(CategoryState())
    val categoryState = _categoryState.asStateFlow()

    private val _favoriteRestaurantState = MutableStateFlow(FavoriteRestaurantState())
    val favoriteRestaurantState = _favoriteRestaurantState.asStateFlow()


    fun getRestaurant(restaurantRequest: RestaurantRequest) {
        viewModelScope.launch {
            restaurantUseCase.invoke(restaurantRequest).collect { result ->
                when (result) {
                    is NetworkResult.Failure -> {
                        _restaurantState.value = RestaurantState(
                            data = null,
                            isSucces = false,
                            isLoading = true,
                            isError = result.msg
                        )
                        Log.d("restaurantviewmodel:error", "$result")
                    }

                    NetworkResult.Loading -> {
                        _restaurantState.value = RestaurantState(
                            data = null,
                            isSucces = false,
                            isLoading = true,
                            isError = ""
                        )
                    }

                    is NetworkResult.Success -> {
                        val currentData = _restaurantState.value.data ?: emptyList()
                        val newRestaurants = result.data.value?.restaurants ?: emptyList()
                        val restaurantListInfo = result.data.value
                        _restaurantState.value = RestaurantState(
                            data = currentData + newRestaurants,
                            restaurantListInfo = restaurantListInfo,
                            isSucces = true,
                            isLoading = false,
                            isError = ""
                        )

                        Log.d("restaurantviewmodel:succes", newRestaurants.toString())
                        Log.d("restaurantviewmodel:currentData", currentData.toString())
                    }


                }
            }
        }
    }

    fun getLocationInfo() {
        viewModelScope.launch {
            val responseLocaiton = getLocaitonInfoUseCase.invoke()
            Log.d("responseLocaiton:", "$responseLocaiton")
            _locationState.value = LocationState(
                data = responseLocaiton
            )
        }
    }

    fun getCategoryList(categoryRequest: CategoryRequest) {
        viewModelScope.launch {
            categoryUseCase.invoke(categoryRequest).collect { resultCategory ->
                when (resultCategory) {
                    is NetworkResult.Failure -> {
                        _categoryState.value = CategoryState(
                            data = null,
                            isSucces = false,
                            isLoading = false,
                            isError = resultCategory.msg
                        )
                        Log.d("restaurantviewmodel:", "$resultCategory")
                    }

                    NetworkResult.Loading -> {
                        _categoryState.value = CategoryState(
                            data = null,
                            isSucces = false,
                            isLoading = true,
                            isError = ""
                        )
                    }

                    is NetworkResult.Success -> {
                        _categoryState.value = CategoryState(
                            data = resultCategory.data.value,
                            isSucces = true,
                            isLoading = false,
                            isError = ""
                        )
                        Log.d("restaurantviewmodel:", resultCategory.data.value.toString())
                    }
                }
            }
        }
    }

    fun getFavoriteRestaurant(favoritesRestaurantRequest: FavoritesRestaurantRequest) {
        viewModelScope.launch {
            favoriteRestaurantUseCase.invoke(favoritesRestaurantRequest).collect { result ->
                when (result) {
                    is NetworkResult.Failure -> {
                        _favoriteRestaurantState.value = FavoriteRestaurantState(
                            data = null,
                            isSuccess = false,
                            isLoading = true,
                            isError = result.msg
                        )
                        Log.d("favoriteRestaurantViewModel:error:", "${result.msg}")
                    }

                    NetworkResult.Loading -> {
                        _favoriteRestaurantState.value = FavoriteRestaurantState(
                            data = null,
                            isSuccess = false,
                            isLoading = true,
                            isError = ""
                        )
                    }

                    is NetworkResult.Success -> {
                        _favoriteRestaurantState.value = FavoriteRestaurantState(
                            data = result.data.value,
                            isSuccess = false,
                            isLoading = true,
                            isError = ""
                        )
                        Log.d("favoriteRestaurantViewModel:success:", "${result.data.value}")

                    }
                }
            }
        }
    }

}

data class RestaurantState(
    val data: List<Restaurant?>? = null,
    val restaurantListInfo: RestaurantList? = null, // Ekledik
    val isSucces: Boolean? = false,
    val isLoading: Boolean? = false,
    val isError: String? = ""
)

data class LocationState(
    val data: UserLocationModel? = null
)

data class CategoryState(
    val data: List<Category?>? = null,
    val isSucces: Boolean? = false,
    val isLoading: Boolean? = false,
    val isError: String? = ""
)

data class FavoriteRestaurantState(
    val data: List<FavoriteRestaurant?>? = null,
    val isSuccess: Boolean? = false,
    val isLoading: Boolean? = false,
    val isError: String? = ""
)