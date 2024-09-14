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
import com.example.composedailyexpensetracker.data.Expense
import com.example.composedailyexpensetracker.ui.theme.ComposeDailyExpenseTrackerTheme
import com.example.composedailyexpensetracker.ui.theme.screens.MainScreen
import com.example.composedailyexpensetracker.viewmodel.ExpenseViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val expenseViewModel = ViewModelProvider(this)[ExpenseViewModel::class.java]
        expenseViewModel.addExpense()
        //enableEdgeToEdge()
        setContent {
            ComposeDailyExpenseTrackerTheme {
                /*Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    *//*Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding),
                        expenseViewModel
                    )*//*
                    MainScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }*/
                MainScreen()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, expenseViewModel: ExpenseViewModel) {
    /*Text(
        text = "Hello $name!",
        modifier = modifier
    )

    Button(
        modifier = Modifier.
    ) { }*/
    val expenseList by expenseViewModel.expenseList.observeAsState()

    expenseList?.let {
        LazyColumn(
            content = {
                itemsIndexed(it) {index: Int, item: Expense ->
                    ExpenseRow(item)
                }
            }
        )
    }?: Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        text = "No items yet",
        fontSize = 16.sp
    )
}

@Composable
fun ExpenseRow(expense: Expense) {
    Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Text(
            text = expense.agenda + " -> " + expense.amount,
            color = Color.Green
        )
    }
}

/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeDailyExpenseTrackerTheme {
        Greeting("Android")
    }
}*/
