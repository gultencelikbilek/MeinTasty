package com.example.meintasty.feature.common_screen_feature.canton_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meintasty.domain.model.db_model.RestaurantAccountModel
import com.example.meintasty.domain.model.db_model.UserAccountModel
import com.example.meintasty.domain.usecase.CantonUseCase
import com.example.meintasty.domain.usecase.InsertCantonUseCase
import com.example.meintasty.domain.model.canton_model.response_model.Canton
import com.example.meintasty.domain.model.canton_model.request_model.CantonRequestModel
import com.example.meintasty.domain.model.canton_model.response_model.City
import com.example.meintasty.domain.model.db_model.UserLocationModel
import com.example.meintasty.domain.usecase.restaurant_usecase.GetRestaurantTokenUseCase
import com.example.meintasty.domain.usecase.user_usecase.GetUserDatabaseUseCase
import com.example.meintasty.feature.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class CantonViewModel @Inject constructor(
    private val cantonUseCase: CantonUseCase,
    private val insertCantonUseCase: InsertCantonUseCase,
    private val getRestaurantTokenUseCase: GetRestaurantTokenUseCase,
    private val getUserDatabaseUseCase: GetUserDatabaseUseCase
    ) : ViewModel() {

    private val _canton =MutableStateFlow(CantonState())
    val canton= _canton.asStateFlow()

    private val _cities = MutableStateFlow<List<City>>(emptyList())
    val cities: StateFlow<List<City>> = _cities
    private val _splashShow = MutableStateFlow(UserTokenCantonState())
    val splashShow = _splashShow.asStateFlow()
    private val _splashRestShow = MutableStateFlow(RestaurantTokenCantonState())
    val splashRestShow = _splashRestShow.asStateFlow()

    init {
        runBlocking {
            getToken()
            getRestaurantToken()
        }

    }

    suspend fun getCanton(requestModel: CantonRequestModel) {
        viewModelScope.launch {
            cantonUseCase.invoke(requestModel).collect{result->
                when(result){
                    is NetworkResult.Failure -> {
                        _canton.value = CantonState(
                            data = null,
                            isSucces = false,
                            isLoading = true,
                            isError = result.msg
                        )
                    }
                    NetworkResult.Loading -> {
                        _canton.value = CantonState(
                            data = null,
                            isSucces = false,
                            isLoading = true,
                            isError = ""
                        )
                    }
                    is NetworkResult.Success -> {
                        _canton.value = CantonState(
                            data = result.data.value,
                            isSucces = false,
                            isLoading = true,
                            isError = ""
                        )
                    }
                }
            }
        }
    }

    fun updateCities(selectedCanton: Canton) {
        viewModelScope.launch {
            _cities.value = selectedCanton.cities
            Log.d("selectedCanton.cities:","${selectedCanton.cities}")
        }
    }

    fun saveCantonCities(userLocationModel: UserLocationModel) {
        viewModelScope.launch {
            try {
                insertCantonUseCase.invoke(userLocationModel)
            } catch (e: Exception) {
                Log.e("CantonViewModelLocation", "Error saving canton cities: ${e.message}")
            }
        }
    }
    private suspend fun getToken() {
        val userAccount = getUserDatabaseUseCase.invoke()
        if (userAccount != null) {
            Log.d("splash:navigate:notnull", userAccount.token.toString())
            _splashShow.value = UserTokenCantonState(
                data = userAccount,
                isNavigateLoginScreen = false,
                error = ""
            )
        } else {
            Log.d("splash:navigate:else:", "userAccount is null")
            _splashShow.value = UserTokenCantonState(
                data = null,
                isNavigateLoginScreen = true,
                error = "User account is not found"
            )
        }
    }

    private suspend fun getRestaurantToken(){
        val restaurantAccountModel = getRestaurantTokenUseCase.invoke()
        if (restaurantAccountModel != null){
            Log.d("splash:navigate:notnull", restaurantAccountModel.token.toString())
            _splashRestShow.value = RestaurantTokenCantonState(
                data = restaurantAccountModel,
                isNavigateLoginScreen = false,
                error = ""
            )
        }else{
            Log.d("splash:navigate:else:", "restaurantAccountModel is null")
            _splashRestShow.value = RestaurantTokenCantonState(
                data = null,
                isNavigateLoginScreen = true,
                error = "User account is not found"
            )
        }
    }
}

data class UserTokenCantonState(
    val data: UserAccountModel? = null,
    val isNavigateLoginScreen: Boolean? = null,
    val error: String? = ""
)
data class RestaurantTokenCantonState(
    val data: RestaurantAccountModel? = null,
    val isNavigateLoginScreen: Boolean? = null,
    val error: String? = ""
)

data class CantonState(
    val data : List<Canton?>? = null,
    val isSucces : Boolean? = false,
    val isLoading : Boolean? = false,
    val isError : String? = ""
)
