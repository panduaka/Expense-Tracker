package com.example.expensetracker.feature_expense.presentation.show_expense.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.expensetracker.feature_expense.domain.util.ExpenseOrder
import com.example.expensetracker.feature_expense.domain.util.OrderType

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    expenseOrder: ExpenseOrder = ExpenseOrder.Category(OrderType.Ascending),
    onOrderChange: (ExpenseOrder) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Category",
                isChecked = expenseOrder is ExpenseOrder.Category,
                onCheck = { onOrderChange(ExpenseOrder.Category(expenseOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Amount",
                isChecked = expenseOrder is ExpenseOrder.Amount,
                onCheck = { onOrderChange(ExpenseOrder.Amount(expenseOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Ascending",
                isChecked = expenseOrder.orderType is OrderType.Ascending,
                onCheck = {
                    onOrderChange(expenseOrder.copy(OrderType.Ascending))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Descending",
                isChecked = expenseOrder.orderType is OrderType.Descending,
                onCheck = {
                    onOrderChange(expenseOrder.copy(OrderType.Descending))
                }
            )
        }
    }
}