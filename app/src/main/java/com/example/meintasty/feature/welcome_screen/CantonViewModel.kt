package com.example.meintasty.feature.welcome_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meintasty.data.repoimpl.CantonRepositoryImpl
import com.example.meintasty.domain.model.Canton
import com.example.meintasty.domain.model.CantonRequestModel
import com.example.meintasty.domain.model.CantonResponseModel
import com.example.meintasty.domain.model.City
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CantonViewModel @Inject constructor(
    private val repositoryImpl: CantonRepositoryImpl
) : ViewModel() {

    private val _canton = MutableStateFlow<List<Canton>>(emptyList())
    val canton: StateFlow<List<Canton>> = _canton

    private val _cities = MutableStateFlow<List<City>>(emptyList())
    val cities: StateFlow<List<City>> = _cities

    suspend fun getCanton(requestModel: CantonRequestModel) {
        viewModelScope.launch {
            try {
                val response = repositoryImpl.getCanton(requestModel)
                if (response.success) {
                    _canton.value = response.value
                    Log.d("CantonViewModel:", response.success.toString())
                } else {
                    Log.e("CantonViewModel", "Error: ${response.errorMessage}")
                    Log.d("CantonViewModel:", response.success.toString())
                }
            } catch (e: Exception) {
                Log.d("CantonViewModel:", e.message.toString())
            }
        }
    }

    fun updateCities(selectedCanton: Canton) {
        viewModelScope.launch {
            _cities.value = selectedCanton.cities
        }
    }
}