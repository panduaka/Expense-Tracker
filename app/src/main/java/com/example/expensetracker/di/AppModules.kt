package com.example.expensetracker.di

import android.app.Application
import androidx.room.Room
import com.example.expensetracker.feature_expense.data.data_source.ExpenseDatabase
import com.example.expensetracker.feature_expense.data.data_source.ExpenseDatabase.Companion.DATABASE_NAME
import com.example.expensetracker.feature_expense.data.repository.ExpenseRepositoryImpl
import com.example.expensetracker.feature_expense.domain.repository.ExpenseRepository
import com.example.expensetracker.feature_expense.domain.use_case.AddExpenseUseCase
import com.example.expensetracker.feature_expense.domain.use_case.DeleteExpenseUseCase
import com.example.expensetracker.feature_expense.domain.use_case.ExpenseUseCases
import com.example.expensetracker.feature_expense.domain.use_case.GetExpensesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModules {

    @Provides
    @Singleton
    fun provideExpenseDatabase(app: Application): ExpenseDatabase {
        return Room.databaseBuilder(
            app,
            ExpenseDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideExpenseRepository(db: ExpenseDatabase): ExpenseRepository {
        return ExpenseRepositoryImpl(db.expenseDao)
    }

    @Provides
    @Singleton
    fun provideExpenseUseCases(repository: ExpenseRepository): ExpenseUseCases {
        return ExpenseUseCases(
            getExpensesUseCase = GetExpensesUseCase(repository),
            deleteExpenseUseCase = DeleteExpenseUseCase(repository),
            addExpenseUseCase = AddExpenseUseCase(repository)
        )
    }

}