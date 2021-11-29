package com.example.expensetracker.feature_expense.domain.use_case

import com.example.expensetracker.feature_expense.domain.model.ExpenseModel
import com.example.expensetracker.feature_expense.domain.repository.ExpenseRepository

class GetExpense(
    private val repository: ExpenseRepository
) {

    suspend operator fun invoke(id: Int): ExpenseModel? {
        return repository.getExpenseById(id)
    }
}