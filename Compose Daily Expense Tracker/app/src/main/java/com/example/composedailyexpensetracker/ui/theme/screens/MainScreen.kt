package com.example.composedailyexpensetracker.ui.theme.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.composedailyexpensetracker.data.Expense
import com.example.composedailyexpensetracker.viewmodel.ExpenseViewModel
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun MainScreen(navController: NavController, expenseViewModel: ExpenseViewModel) {
    val totalCost by expenseViewModel.totalCost.observeAsState()
    val expenseOfKomol by expenseViewModel.expenseOfKomol.observeAsState()
    val expenseOfSukanta by expenseViewModel.expenseOfSukanta.observeAsState()
    val balanceOfKomol by expenseViewModel.balanceOfKomol.observeAsState()
    val balanceOfSukanta by expenseViewModel.balanceOfSukanta.observeAsState()
    val expenseList by expenseViewModel.expenseList.observeAsState()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(8.dp)) {
            Column {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Total cost: BDT " + totalCost,
                    color = Color.Red,
                    fontSize = 24.sp,
                    fontWeight = FontWeight(600),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.heightIn(8.dp))

                Column {
                    PerPersonCalculationHeadline()
                    expenseOfKomol?.let { balanceOfKomol?.let { it1 ->
                        PerPersonCalculation("Komol", it,
                            it1
                        )
                    } }
                    Spacer(modifier = Modifier.heightIn(4.dp))
                    expenseOfSukanta?.let { balanceOfSukanta?.let { it1 ->
                        PerPersonCalculation("Sukanta", it,
                            it1
                        )
                    } }
                    Spacer(modifier = Modifier.heightIn(8.dp))
                }

                Text(
                    modifier = Modifier.fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    text = "Latest expenses:",
                    color = Color.Green,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.heightIn(4.dp))
                ExpenseHeadline()
                expenseList?.let {
                    LazyColumn(content = {
                        itemsIndexed(it) {index: Int, item: Expense ->
                            ExpenseItem(item)
                        }
                    })
                }?: Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "No items yet",
                    fontSize = 16.sp
                )
            }
            FloatingActionButton(
                modifier = Modifier.padding(16.dp)
                    .align(Alignment.BottomEnd),
                containerColor = Color.Green,
                onClick = {
                    navController.navigate("add_expense_screen")
                }
            ) {
                Icon(imageVector = Icons.Filled.Add,
                    contentDescription = "Add expense",
                    tint = Color.White)
            }
        }
    }
}

@Composable
fun PerPersonCalculationHeadline() {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.weight(.4f)
                .align(Alignment.CenterVertically),
            text = "Name",
            textAlign = TextAlign.Left,
            fontSize = 18.sp,
            fontWeight = FontWeight(600)
        )

        Text(
            modifier = Modifier.weight(.3f)
                .align(Alignment.CenterVertically),
            text = "Purchased",
            textAlign = TextAlign.Left,
            fontSize = 18.sp,
            fontWeight = FontWeight(600)
        )

        Text(
            modifier = Modifier.weight(.3f)
                .align(Alignment.CenterVertically),
            text = "Balance",
            textAlign = TextAlign.Left,
            fontSize = 18.sp,
            fontWeight = FontWeight(600)
        )
    }
}

@Composable
fun PerPersonCalculation(name: String, expense: Int, balance: Int) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.weight(.4f)
                .align(Alignment.CenterVertically),
            text = name,
            textAlign = TextAlign.Left
        )

        Text(
            modifier = Modifier.weight(.3f)
                .align(Alignment.CenterVertically),
            text = "BDT " + expense,
            textAlign = TextAlign.Left
        )

        Text(
            modifier = Modifier.weight(.3f)
                .align(Alignment.CenterVertically),
            text = "BDT " + balance,
            textAlign = TextAlign.Left
        )
    }
}

@Composable
fun ExpenseHeadline() {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.weight(.3f)
                .align(Alignment.CenterVertically),
            text = "Date",
            textAlign = TextAlign.Left,
            fontSize = 18.sp,
            fontWeight = FontWeight(600)
        )

        Text(
            modifier = Modifier.weight(.4f)
                .align(Alignment.CenterVertically),
            text = "Name",
            textAlign = TextAlign.Left,
            fontSize = 18.sp,
            fontWeight = FontWeight(600)
        )

        Text(
            modifier = Modifier.weight(.3f)
                .align(Alignment.CenterVertically),
            text = "Amount",
            textAlign = TextAlign.Left,
            fontSize = 18.sp,
            fontWeight = FontWeight(600)
        )
    }
}

@Composable
fun ExpenseItem(expense: Expense) {
    Card(modifier = Modifier.fillMaxWidth().padding(4.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            val date = Date(expense.date)
            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
            val date_str = simpleDateFormat.format(date)
            Text(
                modifier = Modifier.weight(.3f)
                    .align(Alignment.CenterVertically),
                text = date_str,
                textAlign = TextAlign.Left
            )

            Text(
                modifier = Modifier.weight(.4f)
                    .align(Alignment.CenterVertically),
                text = expense.name,
                textAlign = TextAlign.Left
            )

            Text(
                modifier = Modifier.weight(.3f)
                    .align(Alignment.CenterVertically),
                text = "BDT " + expense.amount,
                textAlign = TextAlign.Left
            )
        }
    }
}