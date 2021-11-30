package com.example.expensetracker.feature_expense.domain.util

sealed class ExpenseCategory(category: String) {
    class Health(category: String) : ExpenseCategory(category)
    class Food(category: String) : ExpenseCategory(category)
    class Utilities(category: String) : ExpenseCategory(category)
    class Transport(category: String) : ExpenseCategory(category)
    class Fashion(category: String) : ExpenseCategory(category)
    class Other(category: String) : ExpenseCategory(category)
}