package com.example.expensetracker.feature_expense.data.data_source

import androidx.room.*
import com.example.expensetracker.feature_expense.domain.model.ExpenseModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Query("SELECT * FROM Expenses")
    fun getAllExpenses(): Flow<List<ExpenseModel>>

    @Query("SELECT * FROM Expenses WHERE id = :id")
    suspend fun getExpenseById(id: Int): ExpenseModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expense: ExpenseModel)

    @Delete
    suspend fun deleteExpense(expense: ExpenseModel)
}