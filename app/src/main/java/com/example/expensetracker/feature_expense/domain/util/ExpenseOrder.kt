package com.example.expensetracker.feature_expense.domain.util

sealed class ExpenseOrder(val orderType: OrderType) {
    class Category(orderType: OrderType) : ExpenseOrder(orderType)
    class Amount(orderType: OrderType) : ExpenseOrder(orderType)

    fun copy(orderType: OrderType): ExpenseOrder {
        return when (this) {
            is Category -> Category(orderType)
            is Amount -> Amount(orderType)
        }
    }
}
