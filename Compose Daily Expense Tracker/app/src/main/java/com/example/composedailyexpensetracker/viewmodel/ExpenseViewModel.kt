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

    val addExpenseDate = MutableLiveData<Long>()
    val addExpenseUser = MutableLiveData<String>("Komol")
    val addExpenseAmountInString = MutableLiveData<String>()

    val totalCost = MutableLiveData<Int>(0)
    val expenseOfKomol = MutableLiveData<Int>(0)
    val expenseOfSukanta = MutableLiveData<Int>(0)
    val balanceOfKomol = MutableLiveData<Int>(0)
    val balanceOfSukanta = MutableLiveData<Int>(0)

    val expenseDao = MainApplication.expenseDatabase.getExpenseDao()

    val expenseList : LiveData<List<Expense>> = expenseDao.getAllExpenses()
    val observer = expenseList.observeForever {
        var total = 0
        var kTotal = 0
        var sTotal = 0
        for (i in 0..it.size-1) {
            total += it[i].amount
            if (it[i].name == "Komol") {
                kTotal += it[i].amount
            } else {
                sTotal += it[i].amount
            }
        }

        totalCost.value = total
        expenseOfKomol.value = kTotal
        expenseOfSukanta.value = sTotal
        balanceOfKomol.value = kTotal - (total / 2)
        balanceOfSukanta.value = sTotal - (total / 2)
    }

    fun onExpenderChange(user: String) {
        addExpenseUser.value = user
    }

    fun onExpenseDateChange(date: Long) {
        addExpenseDate.value = date
    }

    fun onExpenseAmountChange(amount: String) {
        addExpenseAmountInString.value = amount
    }

    fun addExpense() {
        viewModelScope.launch(Dispatchers.IO) {
            /*val date = Date.from(Instant.now())
            expenseDao.addExpense(Expense(0,date.time, "Komol", 100))
            expenseDao.addExpense(Expense(0,date.time, "Sukanta", 100))*/
            addExpenseUser.value?.let {
                addExpenseDate.value?.let { it1 ->
                    addExpenseAmountInString.value?.let { it2 ->
                        Expense(0, it1,
                            it, it2.toInt())
                    }
                }
            }?.let {
                expenseDao.addExpense(it)
                resetExpenditureValues()
            }
        }
    }

    fun deleteExpense(id: Int) {
        viewModelScope.launch(Dispatchers.IO){
            expenseDao.delete(id)
        }
    }

    private fun resetExpenditureValues() {
        viewModelScope.launch(Dispatchers.Main.immediate) {
            addExpenseUser.value = "Komol"
            addExpenseDate.value = 0L
            addExpenseAmountInString.value = ""
        }
    }
}