package com.example.expensetracker.feature_expense.domain.use_case

import com.example.expensetracker.feature_expense.domain.model.ExpenseModel
import com.example.expensetracker.feature_expense.domain.repository.ExpenseRepository
import com.example.expensetracker.feature_expense.domain.util.ExpenseOrder
import com.example.expensetracker.feature_expense.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetExpensesUseCase(
    private val expenseRepository: ExpenseRepository
) {

    operator fun invoke(
        expenseOrder: ExpenseOrder = ExpenseOrder.Category(OrderType.Ascending)
    ): Flow<List<ExpenseModel>> {
        return expenseRepository.getExpenses().map { expenses ->
            when (expenseOrder.orderType) {
                is OrderType.Ascending -> {
                    when (expenseOrder) {
                        is ExpenseOrder.Category -> expenses.sortedBy { it.category.lowercase() }
                        is ExpenseOrder.Amount -> expenses.sortedBy { it.amount }
                    }
                }
                is OrderType.Descending -> {
                    when (expenseOrder) {
                        is ExpenseOrder.Category -> expenses.sortedByDescending { it.category.lowercase() }
                        is ExpenseOrder.Amount -> expenses.sortedByDescending { it.amount }
                    }
                }
            }
        }
    }
}