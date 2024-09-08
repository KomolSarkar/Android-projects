package com.example.composedailyexpensetracker.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.composedailyexpensetracker.data.Expense

@Dao
interface ExpenseDao {
    @Query("SELECT * FROM expense")
    fun getAllExpenses(): LiveData<List<Expense>>

    /*@Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>*/

    /*@Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): User*/

    @Insert
    fun addExpense(expense: Expense)

    @Delete
    fun delete(expense: Expense)
}