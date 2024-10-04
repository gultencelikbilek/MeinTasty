package com.example.meintasty.feature.login_screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meintasty.data.repoimpl.LoginUserRepositoryImpl
import com.example.meintasty.domain.model.UserAccountModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repositoryImpl: LoginUserRepositoryImpl
) : ViewModel() {

    private val _tokenState = MutableStateFlow(UserAccountState.Succes(false))
    val tokenState: StateFlow<UserAccountState> = _tokenState


   private val _token = MutableLiveData("null")
   val token: LiveData<String> get() = _token

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            try {

                val token = repositoryImpl.getToken(email, password)
                val userAccountModel = UserAccountModel(
                    0,
                    fullName = token.fullName,
                    roleList = token.roleList,
                    token = token.toString()
                )

                //db ye token kaydet
                //kullanıcıyı bir sonraki ekrana geçiş yaptır
                Log.d("Logg:", token.toString())
                Log.d("Logg:", userAccountModel.toString())
                Log.d("Logg:", "$email $password")

                repositoryImpl.insertToken(userAccountModel)
                withContext(Dispatchers.Main) {
                    _token.value = "Test"
                    _tokenState.value = UserAccountState.Succes(true)
                    Log.d("UserAccountState.Succes", "${UserAccountState.Succes(data = true)}")
                }
            }catch (e:Exception){
                Log.d("exception:",e.message.toString())

            }
        }
    }
}

sealed class UserAccountState {
    data class Succes(val data: Boolean) : UserAccountState()
    data class Error(val exception: Exception) : UserAccountState()
}