package com.example.expensetracker.feature_expense.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.expensetracker.*

@Entity(tableName = "Expenses")
data class ExpenseModel(
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "amount") val amount: Double,
    @ColumnInfo(name = "color") val color: Int,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int?,
) {
    companion object {
        val expenseColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}
class InvalidExpenseException(message: String): Exception(message)
