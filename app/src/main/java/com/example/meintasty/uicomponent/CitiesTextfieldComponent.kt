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
import com.example.meintasty.domain.model.canton_model.City

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CitiesTextFieldComponent(
    citiesList: List<City>,
    citiesSelect : String,
    onCitiesChange :(String) -> Unit
) {
    var isDropDownExpanded by remember { mutableStateOf(false) }
    var selectedCities by remember { mutableStateOf(citiesSelect) }


    ExposedDropdownMenuBox(
        expanded = isDropDownExpanded,
        onExpandedChange = { isDropDownExpanded = !isDropDownExpanded } // tıklamayla açma/kapama
    ) {

        OutlinedTextField(
            value = selectedCities,
            onValueChange = { onCitiesChange(it) },
            label = { Text(text = stringResource(id = R.string.select_cities)) },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(20.dp),
            readOnly = true

        )
        ExposedDropdownMenu(
            expanded = isDropDownExpanded,
            onDismissRequest = { isDropDownExpanded = false }
        ) {
            citiesList.forEach {city ->
                DropdownMenuItem(
                    text = { Text(text = city.cityName) },
                    onClick = {

                        selectedCities = city.cityName
                        onCitiesChange(city.cityName)
                        isDropDownExpanded = false
                    }
                )
            }
        }
    }
}