package com.example.meintasty.feature.splash_screen

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meintasty.data.repoimpl.SplashScreenRepositoryImpl
import com.example.meintasty.domain.model.UserAccountModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class MeinTastyViewModel @Inject constructor(
    private val splashScreenRepositoryImpl: SplashScreenRepositoryImpl
) : ViewModel() {

    private val _splashShow = MutableLiveData(TokenState())
    val splashShow = _splashShow

    init {
        runBlocking {
            getToken()
        }
    }
    private suspend fun getToken() {
        viewModelScope.launch {
            val userAccount = splashScreenRepositoryImpl.getToken()
            Log.d("splash:", userAccount?.token.toString())

            if (userAccount != null){
                Log.d("splash:navigate:t:", "")
                _splashShow.value = TokenState(
                    data = userAccount,
                    isNavigateLoginScreen = false,
                    error = ""
                )
            }else{
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