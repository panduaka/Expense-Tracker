package com.example.expensetracker.feature_expense.domain.util

sealed class ExpenseCategory {
    object Health : ExpenseCategory()
    object Food : ExpenseCategory()
    object Utilities : ExpenseCategory()
    object Transport : ExpenseCategory()
    object Fashion : ExpenseCategory()
    object Other : ExpenseCategory()
}