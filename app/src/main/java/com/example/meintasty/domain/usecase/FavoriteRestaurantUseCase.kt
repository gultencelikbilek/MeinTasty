package com.example.meintasty.domain.usecase

import com.example.meintasty.data.repoimpl.NetworkRepositoryImpl
import com.example.meintasty.domain.model.favorites_restaurants_model.favorite_restauranst_request.FavoritesRestaurantRequest
import com.example.meintasty.domain.model.favorites_restaurants_model.favorite_restauranst_response.FavoriteRestaurantResponse
import com.example.meintasty.feature.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FavoriteRestaurantUseCase @Inject constructor(private val networkRepositoryImpl: NetworkRepositoryImpl) {

    operator suspend fun invoke(favoritesRestaurantRequest: FavoritesRestaurantRequest) : Flow<NetworkResult<FavoriteRestaurantResponse>> = flow {
        try {
            emit(NetworkResult.Loading)
            val response = networkRepositoryImpl.getFavoritesRestaurant(favoritesRestaurantRequest)
            emit(NetworkResult.Success(response))
        }catch (e: HttpException){
            emit(NetworkResult.Failure(e.message()))
        }catch (e:IOException){
            emit(NetworkResult.Failure(e.message.toString()))
        }
    }
}