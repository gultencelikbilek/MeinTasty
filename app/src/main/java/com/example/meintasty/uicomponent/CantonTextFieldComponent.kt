package com.example.meintasty.uicomponent

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextFieldDefaults
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.meintasty.R
import com.example.meintasty.domain.model.canton_model.response_model.Canton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CantonTextFieldComponent(
    cantonList: List<Canton>, // ViewModel'den gelen kanton listesi
    cantonSelect: String,
    onCantonChange: (String) -> Unit
) {
    var isDropDownExpanded by remember { mutableStateOf(false) }
    var selectedCanton by remember { mutableStateOf(cantonSelect) }


    ExposedDropdownMenuBox(
        expanded = isDropDownExpanded,
        onExpandedChange = { isDropDownExpanded = !isDropDownExpanded }
    ) {

        OutlinedTextField(
            value = selectedCanton,
            onValueChange = { onCantonChange(it) },
            label = { Text(text = stringResource(id = R.string.select_canton)) },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(20.dp),
            readOnly = true,

        )
        ExposedDropdownMenu(
            expanded = isDropDownExpanded,
            onDismissRequest = { isDropDownExpanded = false }
        ) {
            cantonList.forEach {canton ->
                DropdownMenuItem(
                    text = { Text(text = canton.cantonName) },
                    onClick = {
                        selectedCanton = canton.cantonName
                        onCantonChange(canton.cantonName)
                        isDropDownExpanded = false
                    }
                )
            }
        }
    }
}

