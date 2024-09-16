package com.example.composedailyexpensetracker

import android.app.Application
import androidx.room.Room
import com.example.composedailyexpensetracker.database.ExpenseDatabase

class MainApplication: Application() {
    companion object {
        lateinit var expenseDatabase: ExpenseDatabase
    }

    override fun onCreate() {
        super.onCreate()
        // Required initialization logic here!
        expenseDatabase = Room.databaseBuilder(
            applicationContext,
            ExpenseDatabase::class.java,
            ExpenseDatabase.NAME
        ).fallbackToDestructiveMigration().build()
    }
}