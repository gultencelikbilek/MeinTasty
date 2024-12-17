package com.example.meintasty.uicomponent

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.meintasty.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> DropdownTextFieldComponent(
    label: String,
    list: List<T?>?, // Veri listesi
    selectedItem: String, // Seçilen değer
    onItemSelected: (String) -> Unit, // Seçim yapıldığında çağrılan lambda
    labelSelector: (T) -> String // Görüntülenecek veriyi seçen lambda
) {
    var isDropDownExpanded by remember { mutableStateOf(false) }
    var selectedValue by remember { mutableStateOf(selectedItem) }

    ExposedDropdownMenuBox(
        expanded = isDropDownExpanded,
        onExpandedChange = { isDropDownExpanded = !isDropDownExpanded }
    ) {

        OutlinedTextField(
            value = selectedValue,
            onValueChange = {},
            label = {
                Text(
                    text = label, style = TextStyle(
                        color = colorResource(id = R.color.black)
                    )
                )
            },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .wrapContentHeight(),
            shape = RoundedCornerShape(20.dp),
            readOnly = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorResource(id = R.color.mein_tasty_color)
            )
        )

        ExposedDropdownMenu(
            expanded = isDropDownExpanded,
            onDismissRequest = { isDropDownExpanded = false }
        ) {
            list?.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = labelSelector(item!!)) },
                    onClick = {
                        selectedValue = item?.let { labelSelector(it) }.toString()
                        item?.let { labelSelector(it) }?.let { onItemSelected(it) }
                        isDropDownExpanded = false
                    }
                )
            }
        }
    }
}
