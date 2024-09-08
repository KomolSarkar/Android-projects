package com.example.composedailyexpensetracker.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Expense(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "agenda") var agenda: String,
    @ColumnInfo(name = "amount") var amount: Int
)