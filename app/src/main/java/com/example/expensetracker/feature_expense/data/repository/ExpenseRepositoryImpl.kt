package com.example.expensetracker.feature_expense.data.repository

import com.example.expensetracker.feature_expense.data.data_source.ExpenseDao
import com.example.expensetracker.feature_expense.domain.model.ExpenseModel
import com.example.expensetracker.feature_expense.domain.repository.ExpenseRepository
import kotlinx.coroutines.flow.Flow

class ExpenseRepositoryImpl(
    private val dao: ExpenseDao
) : ExpenseRepository {
    override fun getExpenses(): Flow<List<ExpenseModel>> {
        return dao.getAllExpenses()
    }

    override suspend fun getExpenseById(id: Int): ExpenseModel? {
        return dao.getExpenseById(id)
    }

    override suspend fun insertExpense(expense: ExpenseModel) {
        dao.insertExpense(expense)
    }

    override suspend fun deleteExpense(expense: ExpenseModel) {
        dao.deleteExpense(expense)
    }
}