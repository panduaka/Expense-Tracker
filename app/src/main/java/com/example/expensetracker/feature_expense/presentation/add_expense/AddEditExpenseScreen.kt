package com.example.expensetracker.feature_expense.presentation.add_expense

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.expensetracker.feature_expense.domain.model.ExpenseModel
import com.example.expensetracker.feature_expense.presentation.add_expense.components.CategorySelection
import com.example.expensetracker.feature_expense.presentation.add_expense.components.TransparentHintAmountField
import com.example.expensetracker.feature_expense.presentation.add_expense.components.TransparentHintTextField
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun AddEditExpenseScreen(
    navController: NavController,
    expenseColor: Int,
    viewModel: AddExpenseViewModel = hiltViewModel()
) {
    val titleState = viewModel.expenseTitle.value
    val categoryState = viewModel.category.value
    val amountState = viewModel.amount.value

    val scaffoldState = rememberScaffoldState()

    val expenseBackgroundAnimatable = remember {
        Animatable(
            Color(if (expenseColor != -1) expenseColor else viewModel.expenseColor.value)
        )
    }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddExpenseViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is AddExpenseViewModel.UiEvent.SaveExpense -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(AddEditExpenseEvent.SaveExpense)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "Save expense")
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(expenseBackgroundAnimatable.value)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ExpenseModel.expenseColors.forEach { color ->
                    val colorInt = color.toArgb()
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .shadow(15.dp, CircleShape)
                            .clip(CircleShape)
                            .background(color)
                            .border(
                                width = 3.dp,
                                color = if (viewModel.expenseColor.value == colorInt) {
                                    Color.Black
                                } else Color.Transparent,
                                shape = CircleShape
                            )
                            .clickable {
                                scope.launch {
                                    expenseBackgroundAnimatable.animateTo(
                                        targetValue = Color(colorInt),
                                        animationSpec = tween(
                                            durationMillis = 500
                                        )
                                    )
                                }
                                viewModel.onEvent(AddEditExpenseEvent.ChangeColor(colorInt))
                            }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(
                text = titleState.text,
                hint = titleState.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditExpenseEvent.EnteredTitle(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditExpenseEvent.ChangeTitleFocus(it))
                },
                isHintVisible = titleState.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.h5
            )
            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintAmountField(
                text = amountState.text,
                hint = amountState.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditExpenseEvent.EnterAmount(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditExpenseEvent.ChangeAmountFocus(it))
                },
                isHintVisible = categoryState.isHintVisible,
                textStyle = MaterialTheme.typography.body2,
            )
            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(
                text = categoryState.text,
                hint = categoryState.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditExpenseEvent.EnteredCategory(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditExpenseEvent.ChangeCategoryFocus(it))
                },
                isHintVisible = categoryState.isHintVisible,
                textStyle = MaterialTheme.typography.body1,
//                modifier = Modifier.fillMaxHeight()
            )
            Spacer(modifier = Modifier.height(16.dp))
            CategorySelection()
        }
    }
}