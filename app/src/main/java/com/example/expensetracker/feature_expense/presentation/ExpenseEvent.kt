package com.example.expensetracker.feature_expense.presentation

import com.example.expensetracker.feature_expense.domain.model.ExpenseModel
import com.example.expensetracker.feature_expense.domain.util.ExpenseOrder

sealed class ExpenseEvent {
    data class Order(val expenseOrder: ExpenseOrder) : ExpenseEvent()
    data class DeleteNote(val expense: ExpenseModel) : ExpenseEvent()
    object ToggleOrderSection : ExpenseEvent()
}
