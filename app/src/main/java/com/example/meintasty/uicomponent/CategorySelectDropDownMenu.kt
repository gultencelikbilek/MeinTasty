package com.example.meintasty.uicomponent

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.meintasty.R
import com.example.meintasty.domain.model.category_detail_model.category_detail_response.Category
import com.example.meintasty.domain.model.restaurant_detail.restaurant_detail_response.Menu

@Composable
fun CategorySelectDropDownMenu(
    categoryList: List<Menu?>?,
    categorySelect: String,
    onCategoryChange: (String) -> Unit
) {
    DropdownTextFieldComponent(
        label = categorySelect,
        list = categoryList,
        selectedItem =categorySelect,
        onItemSelected = onCategoryChange,
        labelSelector = {menu->
            menu?.let {category->
                category.categoryName
            }.toString()
        }
    )
}