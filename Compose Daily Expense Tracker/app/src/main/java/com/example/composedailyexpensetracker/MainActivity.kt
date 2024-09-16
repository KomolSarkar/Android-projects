package com.example.composedailyexpensetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composedailyexpensetracker.data.Expense
import com.example.composedailyexpensetracker.ui.theme.ComposeDailyExpenseTrackerTheme
import com.example.composedailyexpensetracker.ui.theme.screens.AddExpenseScreen
import com.example.composedailyexpensetracker.ui.theme.screens.MainScreen
import com.example.composedailyexpensetracker.viewmodel.ExpenseViewModel
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val expenseViewModel = ViewModelProvider(this)[ExpenseViewModel::class.java]
        //enableEdgeToEdge()
        setContent {
            ComposeDailyExpenseTrackerTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "main_screen", builder = {
                    composable("main_screen") {
                        MainScreen(navController, expenseViewModel)
                    }
                    composable("add_expense_screen") {
                        AddExpenseScreen(navController, expenseViewModel)
                    }
                })
            }
        }
    }
}
