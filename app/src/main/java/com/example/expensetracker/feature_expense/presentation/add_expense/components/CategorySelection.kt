package com.example.expensetracker.feature_expense.presentation.add_expense.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val categoryList = mutableListOf("Health", "Food", "Utilities", "Transport", "Fashion", "Other")

@Composable
fun CategorySelection(
    textStyle: TextStyle = TextStyle()
) {

    var categoryName: String by remember { mutableStateOf(categoryList[0]) }
    var expanded by remember { mutableStateOf(false) }

    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
        Row(
            Modifier
                .padding(24.dp)
                .clickable {
                    expanded = !expanded
                }
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = categoryName,
                fontSize = 18.sp,
                modifier = Modifier.padding(end = 8.dp)
            )
            Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "")
            DropdownMenu(expanded = expanded, onDismissRequest = {
                expanded = false
            }) {
                categoryList.forEach { category ->
                    DropdownMenuItem(onClick = {
                        expanded = false
                        categoryName = category
                    }) {
                        Text(text = category)
                    }
                }
            }
        }
    }
}