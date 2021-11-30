package com.example.expensetracker.feature_expense.presentation.add_expense

import androidx.compose.ui.focus.FocusState

sealed class AddEditExpenseEvent {
    data class EnteredTitle(val value: String) : AddEditExpenseEvent()
    data class ChangeTitleFocus(val focusState: FocusState) : AddEditExpenseEvent()
    data class EnteredCategory(val value: String) : AddEditExpenseEvent()
    data class ChangeCategoryFocus(val focusState: FocusState) : AddEditExpenseEvent()
    data class EnterAmount(val value: String) : AddEditExpenseEvent()
    data class ChangeAmountFocus(val focusState: FocusState) : AddEditExpenseEvent()
    data class ChangeColor(val color: Int) : AddEditExpenseEvent()
    object SaveExpense : AddEditExpenseEvent()
}

