package com.example.composedailyexpensetracker.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.composedailyexpensetracker.data.Expense

@Database(entities = [Expense::class], version = 2)
abstract class ExpenseDatabase: RoomDatabase() {

    companion object {
        const val NAME = "Expense_DB"
    }

    abstract fun getExpenseDao(): ExpenseDao
}