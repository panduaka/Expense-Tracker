package com.example.expensetracker.feature_expense.domain.util

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}
