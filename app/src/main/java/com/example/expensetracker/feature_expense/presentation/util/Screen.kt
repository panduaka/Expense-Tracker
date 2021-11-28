package com.example.expensetracker.feature_expense.presentation.util

sealed class Screen(val route: String) {
    object ExpenseScreen : Screen("expense_screen")
    object AddExpenseScreen : Screen("add_expense_screen")
}
