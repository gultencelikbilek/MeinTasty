package com.example.meintasty.feature.splash_screen

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meintasty.data.repoimpl.SplashScreenRepositoryImpl
import com.example.meintasty.data.usecase.GetLocaitonInfoUseCase
import com.example.meintasty.data.usecase.SplashUseCase
import com.example.meintasty.domain.model.UserAccountModel
import com.example.meintasty.domain.model.UserLocationModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class MeinTastyViewModel @Inject constructor(
    private val splashScreenRepositoryImpl: SplashScreenRepositoryImpl,
    private val getLocaitonInfoUseCase: GetLocaitonInfoUseCase,
    private val splashUseCase: SplashUseCase
) : ViewModel() {

    private val _splashShow = MutableLiveData(TokenState())
    val splashShow = _splashShow

    private val _locaState = MutableStateFlow(LocationState())
    val locaState = _locaState.asStateFlow()
    init {
        runBlocking {
            getToken()
        }
    }

    suspend fun getLocation(){
        viewModelScope.launch {
            val locationInfo = getLocaitonInfoUseCase.invoke()
            Log.d("splash:",locationInfo.toString())
            Log.d("splash:", locationInfo.cityName.toString())
            Log.d("splash:", locationInfo.cantonName.toString())
            if (locationInfo != null) {
                val loca = UserLocationModel(0, locationInfo.cantonName, locationInfo.cityName)
                _locaState.value = LocationState(
                    data = loca
                )
            }
        }
    }
    private suspend fun getToken() {
        viewModelScope.launch {
            val userAccount = splashUseCase.invoke()
            Log.d("splash:", userAccount?.token.toString())
            if (userAccount != null) {
                    Log.d("splash:navigate:t:", "")
                    _splashShow.value = TokenState(
                        data = userAccount,
                        isNavigateLoginScreen = false,
                        error = ""
                    )
                    Log.d("splashLoc:navigate:f:", "")
                    _splashShow.value = TokenState(
                        data = userAccount,
                        isNavigateLoginScreen = true,
                        error = ""
                    )
            } else {
                Log.d("splash:navigate:f:", "")
                _splashShow.value = TokenState(
                    data = userAccount,
                    isNavigateLoginScreen = true,
                    error = ""
                )
            }
        }
    }
}



data class TokenState(
    val data: UserAccountModel? = null,
    val isNavigateLoginScreen: Boolean? = null,
    val error: String? = ""
)

data class LocationState(
    val data: UserLocationModel? = null,
    val isNavigateLoginScreen: Boolean? = null,

    )