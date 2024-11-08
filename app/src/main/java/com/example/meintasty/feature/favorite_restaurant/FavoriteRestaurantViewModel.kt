package com.example.meintasty.feature.favorite_restaurant

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meintasty.domain.model.favorites_restaurants_model.favorite_restauranst_request.FavoritesRestaurantRequest
import com.example.meintasty.domain.model.favorites_restaurants_model.favorite_restauranst_response.FavoriteRestaurant
import com.example.meintasty.domain.model.favorites_restaurants_model.favorite_restauranst_response.FavoriteRestaurantResponse
import com.example.meintasty.domain.usecase.FavoriteRestaurantUseCase
import com.example.meintasty.feature.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteRestaurantViewModel @Inject constructor(
    private val favoriteRestaurantUseCase: FavoriteRestaurantUseCase
): ViewModel(){

    private val _favoriteRestaurantState = MutableStateFlow(FavoriteRestaurantState())
    val favoriteRestaurantState = _favoriteRestaurantState.asStateFlow()

    fun getFavoriteRestaurant(favoritesRestaurantRequest: FavoritesRestaurantRequest){
        viewModelScope.launch {
            favoriteRestaurantUseCase.invoke(favoritesRestaurantRequest).collect{result ->
                when(result){
                    is NetworkResult.Failure -> {
                        _favoriteRestaurantState.value =  FavoriteRestaurantState(
                            data = null,
                            isSuccess = false,
                            isLoading = true,
                            isError = result.msg
                        )
                        Log.d("favoriteRestaurantViewModel:error:","${result.msg}")
                    }
                    NetworkResult.Loading -> {
                        _favoriteRestaurantState.value =  FavoriteRestaurantState(
                            data = null,
                            isSuccess = false,
                            isLoading = true,
                            isError = ""
                        )
                    }
                    is NetworkResult.Success -> {
                        _favoriteRestaurantState.value =  FavoriteRestaurantState(
                            data = result.data.value,
                            isSuccess = false,
                            isLoading = true,
                            isError = ""
                        )
                        Log.d("favoriteRestaurantViewModel:success:","${result.data.value}")

                    }
                }
            }
        }
    }


}
data class FavoriteRestaurantState(
    val data : List<FavoriteRestaurant?>? = null,
    val isSuccess: Boolean? = false,
    val isLoading : Boolean? = false,
    val isError : String? = ""
)