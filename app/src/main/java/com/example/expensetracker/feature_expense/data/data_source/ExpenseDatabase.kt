package com.example.expensetracker.feature_expense.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.expensetracker.feature_expense.domain.model.ExpenseModel

@Database(
    entities = [ExpenseModel::class], version = 1, exportSchema = false
)
abstract class ExpenseDatabase : RoomDatabase() {
    abstract val expenseDao: ExpenseDao

    companion object {
        const val DATABASE_NAME = "expense_db"
    }
}