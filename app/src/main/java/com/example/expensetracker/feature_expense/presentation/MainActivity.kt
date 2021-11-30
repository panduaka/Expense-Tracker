package com.example.expensetracker.feature_expense.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.expensetracker.feature_expense.presentation.add_expense.AddEditExpenseScreen
import com.example.expensetracker.feature_expense.presentation.show_expense.components.ExpenseScreen
import com.example.expensetracker.feature_expense.presentation.util.Screen
import com.example.expensetracker.ui.theme.CleanArchitectureExpenseAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CleanArchitectureExpenseAppTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.ExpenseScreen.route
                    ) {
                        composable(route = Screen.ExpenseScreen.route) {
                            ExpenseScreen(navController = navController)
                        }
                        composable(
                            route = Screen.AddExpenseScreen.route +
                                    "?expenseId={expenseId}&expenseColor={expenseColor}",
                            arguments = listOf(
                                navArgument(
                                    name = "expenseId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                                navArgument(
                                    name = "expenseColor"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                            )
                        ) {
                            val color = it.arguments?.getInt("expenseColor") ?: -1
                            AddEditExpenseScreen(
                                navController = navController,
                                expenseColor = color
                            )
                        }
                    }
                }
            }
        }
    }
}