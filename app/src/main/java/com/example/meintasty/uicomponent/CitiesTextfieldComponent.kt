package com.example.meintasty.uicomponent

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.meintasty.R
import com.example.meintasty.domain.model.canton_model.response_model.City

@Composable
fun CitiesTextFieldComponent(
    citiesList: List<City>,
    citiesSelect: String,
    onCitiesChange: (String) -> Unit
) {
    DropdownTextFieldComponent(
        label = stringResource(id = R.string.select_cities),
        list = citiesList,
        selectedItem = citiesSelect,
        onItemSelected = onCitiesChange,
        labelSelector = { it.cityName }
    )
}
