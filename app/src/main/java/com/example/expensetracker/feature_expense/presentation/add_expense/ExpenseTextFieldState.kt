package com.example.expensetracker.feature_expense.presentation.add_expense

data class ExpenseTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)
