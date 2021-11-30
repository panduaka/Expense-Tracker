package com.example.expensetracker.feature_expense.presentation.add_expense

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.feature_expense.domain.model.ExpenseModel
import com.example.expensetracker.feature_expense.domain.model.InvalidExpenseException
import com.example.expensetracker.feature_expense.domain.use_case.ExpenseUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddExpenseViewModel @Inject constructor(
    private val expenseUseCases: ExpenseUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _expenseTitle = mutableStateOf(
        ExpenseTextFieldState(
            hint = "Enter expense title..."
        )
    )
    val expenseTitle: State<ExpenseTextFieldState> = _expenseTitle

    private val _category = mutableStateOf(
        ExpenseTextFieldState(
            hint = "Enter category"
        )
    )
    val category: State<ExpenseTextFieldState> = _category

    private val _amount = mutableStateOf(
        ExpenseTextFieldState(
            hint = "Enter amount..."
        )
    )
    val amount: State<ExpenseTextFieldState> = _amount

    private val _expenseColor = mutableStateOf(ExpenseModel.expenseColors.random().toArgb())
    val expenseColor: State<Int> = _expenseColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentExpenseId: Int? = null

    init {
        savedStateHandle.get<Int>("expenseId")?.let { expenseId ->
            if (expenseId != -1) {
                viewModelScope.launch {
                    expenseUseCases.getExpense(expenseId)?.also { expense ->
                        currentExpenseId = expense.id
                        _expenseTitle.value = expenseTitle.value.copy(
                            text = expense.title,
                            isHintVisible = false
                        )
                        _category.value = _category.value.copy(
                            text = expense.category,
                            isHintVisible = false
                        )
                        _amount.value = _amount.value.copy(
                            text = expense.amount.toString(),
                            isHintVisible = false
                        )
                        _expenseColor.value = expense.color
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditExpenseEvent) {
        when (event) {
            is AddEditExpenseEvent.EnteredTitle -> {
                _expenseTitle.value = expenseTitle.value.copy(
                    text = event.value
                )
            }
            is AddEditExpenseEvent.ChangeTitleFocus -> {
                _expenseTitle.value = expenseTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            expenseTitle.value.text.isBlank()
                )
            }
            is AddEditExpenseEvent.EnteredCategory -> {
                _category.value = _category.value.copy(
                    text = event.value
                )
            }
            is AddEditExpenseEvent.ChangeCategoryFocus -> {
                _category.value = _category.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            _category.value.text.isBlank()
                )
            }
            is AddEditExpenseEvent.EnterAmount -> {
                _amount.value = _amount.value.copy(
                    text = event.value
                )
            }
            is AddEditExpenseEvent.ChangeAmountFocus -> {
                _amount.value = _amount.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            _amount.value.text.isBlank()
                )
            }

            is AddEditExpenseEvent.ChangeColor -> {
                _expenseColor.value = event.color
            }
            is AddEditExpenseEvent.SaveExpense -> {
                viewModelScope.launch {
                    try {
                        expenseUseCases.addExpenseUseCase(
                            ExpenseModel(
                                title = expenseTitle.value.text,
                                category = category.value.text,
                                amount = 24.00,
                                color = expenseColor.value,
                                id = currentExpenseId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveExpense)
                    } catch (e: InvalidExpenseException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save expense"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        object SaveExpense : UiEvent()
    }
}