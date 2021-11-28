package com.example.expensetracker.feature_expense.presentation

import com.example.expensetracker.feature_expense.domain.model.ExpenseModel
import com.example.expensetracker.feature_expense.domain.util.ExpenseOrder
import com.example.expensetracker.feature_expense.domain.util.OrderType

data class ExpensesState(
    val expenses: List<ExpenseModel> = emptyList(),
    val expenseOrder: ExpenseOrder = ExpenseOrder.Category(OrderType.Ascending),
    val isOrderSectionVisible: Boolean = false
)
