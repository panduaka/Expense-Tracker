package com.example.expensetracker.feature_expense.domain.use_case

import com.example.expensetracker.feature_expense.domain.model.ExpenseModel
import com.example.expensetracker.feature_expense.domain.repository.ExpenseRepository

class AddExpenseUseCase(
    private val expenseRepository: ExpenseRepository
) {
    suspend operator fun invoke(expenseModel: ExpenseModel) {
        expenseRepository.insertExpense(expenseModel)
    }
}