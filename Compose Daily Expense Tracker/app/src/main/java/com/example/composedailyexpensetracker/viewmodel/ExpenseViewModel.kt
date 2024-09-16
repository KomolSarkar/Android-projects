package com.example.composedailyexpensetracker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.composedailyexpensetracker.MainApplication
import com.example.composedailyexpensetracker.data.Expense
import com.example.composedailyexpensetracker.database.ExpenseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

class ExpenseViewModel(): ViewModel() {

    val expenseDao = MainApplication.expenseDatabase.getExpenseDao()

    val expenseList : LiveData<List<Expense>> = expenseDao.getAllExpenses()

    fun addExpense(expense: Expense) {
        viewModelScope.launch(Dispatchers.IO) {
            /*val date = Date.from(Instant.now())
            expenseDao.addExpense(Expense(0,date.time, "Komol", 100))
            expenseDao.addExpense(Expense(0,date.time, "Sukanta", 100))*/
        }
    }


}