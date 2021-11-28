package com.example.expensetracker.feature_expense.data.data_source

import androidx.room.Database
import com.example.expensetracker.feature_expense.domain.model.ExpenseModel

@Database(
    entities = [ExpenseModel::class], version = 1, exportSchema = false
)
abstract class ExpenseDatabase {
    abstract val expenseDao: ExpenseDao
}