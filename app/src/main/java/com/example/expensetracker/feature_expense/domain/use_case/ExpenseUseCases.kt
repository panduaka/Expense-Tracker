package com.example.expensetracker.feature_expense.domain.use_case

data class ExpenseUseCases(
    val getExpensesUseCase: GetExpensesUseCase,
    val deleteExpenseUseCase: DeleteExpenseUseCase,
    val addExpenseUseCase: AddExpenseUseCase,
    val getExpense: GetExpense
)
