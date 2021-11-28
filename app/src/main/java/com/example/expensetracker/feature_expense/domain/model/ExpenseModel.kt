package com.example.expensetracker.feature_expense.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Expenses")
data class ExpenseModel(
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "amount") val amount: Double,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
)
