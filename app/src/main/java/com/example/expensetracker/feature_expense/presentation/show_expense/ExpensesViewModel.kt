package com.example.expensetracker.feature_expense.presentation.show_expense

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.expensetracker.feature_expense.domain.use_case.ExpenseUseCases
import com.example.expensetracker.feature_expense.domain.util.ExpenseOrder
import com.example.expensetracker.feature_expense.domain.util.OrderType
import com.example.expensetracker.feature_expense.presentation.ExpenseEvent
import com.example.expensetracker.feature_expense.presentation.ExpensesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpensesViewModel @Inject constructor(
    private val expenseUseCases: ExpenseUseCases
) : ViewModel() {
    private val _state = mutableStateOf(ExpensesState())
    val state: State<ExpensesState> = _state
    private var getExpensesJob: Job? = null

    init {
        getExpenses(ExpenseOrder.Category(OrderType.Ascending))
    }

    fun onEvent(event: ExpenseEvent) {
        when (event) {
            is ExpenseEvent.Order -> {
                if (state.value.expenseOrder::class == event.expenseOrder::class &&
                    state.value.expenseOrder.orderType == event.expenseOrder.orderType
                ) {
                    return
                }
                getExpenses(event.expenseOrder)
            }
            is ExpenseEvent.DeleteExpense -> {
                viewModelScope.launch {
                    expenseUseCases.deleteExpenseUseCase(event.expense)
                }
            }
            is ExpenseEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getExpenses(expenseOrder: ExpenseOrder) {
        getExpensesJob?.cancel()
        getExpensesJob = expenseUseCases.getExpensesUseCase(expenseOrder = expenseOrder)
            .onEach { expenses ->
                _state.value = state.value.copy(
                    expenses = expenses,
                    expenseOrder = expenseOrder
                )
            }
            .launchIn(viewModelScope)
    }
}