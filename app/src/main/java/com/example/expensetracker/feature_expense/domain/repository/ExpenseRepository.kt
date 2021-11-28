package com.example.expensetracker.feature_expense.domain.repository

import com.example.expensetracker.feature_expense.domain.model.ExpenseModel
import kotlinx.coroutines.flow.Flow

interface ExpenseRepository {
    fun getExpenses(): Flow<List<ExpenseModel>>

    suspend fun getExpenseById(id:Int): ExpenseModel?

    suspend fun insertExpense (expense: ExpenseModel)

    suspend fun deleteExpense (expense: ExpenseModel)

}