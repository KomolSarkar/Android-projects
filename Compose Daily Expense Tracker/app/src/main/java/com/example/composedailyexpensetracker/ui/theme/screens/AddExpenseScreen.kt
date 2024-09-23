package com.example.composedailyexpensetracker.ui.theme.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.composedailyexpensetracker.viewmodel.ExpenseViewModel
import java.text.SimpleDateFormat

@Composable
fun AddExpenseScreen(navController: NavController, expenseViewModel: ExpenseViewModel) {
    val users = arrayOf("Komol", "Sukanta")
    val addExpenseDate by expenseViewModel.addExpenseDate.observeAsState(0L)
    val addExpenseUser by expenseViewModel.addExpenseUser.observeAsState(users[0])
    val addExpenseAmount by expenseViewModel.addExpenseAmountInString.observeAsState("")
    var expenseAmount by remember { mutableStateOf("") }

    val openAddUserDialog = remember { mutableStateOf(false) }
    val newUser = remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (openAddUserDialog.value) {
                AddUserDialog(
                    onDismissRequest = {
                        openAddUserDialog.value = false
                    },
                    onUserChange = {
                        newUser.value = it
                    },
                    newUser.value
                )
            }

            DateSelector(onDateChange = {
                expenseViewModel.onExpenseDateChange(it)
            })

            UserBox(users, addExpenseUser, onUserChange = {
                expenseViewModel.onExpenderChange(it)
            })

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp, 4.dp),
                value = addExpenseAmount.toString(),
                onValueChange = {
                    Log.i("Komols amount", it.toString())
                    expenseViewModel.onExpenseAmountChange(it)
                },
                label = {
                    Text(text = "Amount in BDT")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Button(
                onClick = {
                    if (addExpenseDate != 0L || addExpenseAmount != "") {
                        expenseViewModel.addExpense()
                        navController.navigate("main_screen") {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                        }
                    }
                },
                Modifier
                    .fillMaxWidth()
                    .padding(32.dp, 4.dp)
            ) {
                Text(text = "Add expense")
            }

            Button(
                onClick = {
                    openAddUserDialog.value = true
                },
                Modifier
                    .fillMaxWidth()
                    .padding(32.dp, 4.dp)
            ) {
                Text(text = "Add new member")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserBox(users: Array<String>, user: String, onUserChange: (String) -> Unit) {

    var isExpanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp, 4.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = {
                isExpanded = !isExpanded
            }
        ) {
            TextField(
                value = user,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                },
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = {
                    isExpanded = false
                }
            ) {
                users.forEach { user ->
                    DropdownMenuItem(
                        text = {
                            Text(text = user)
                        },
                        onClick = {
                            onUserChange(user)
                            //selectedUser = user
                            isExpanded = false
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateSelector(onDateChange: (Long) -> Unit) {
    var dateResult by remember { mutableStateOf("Select date") }
    val openDialog = remember { mutableStateOf(false) }

    OutlinedButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp, 4.dp),
        onClick = {
            openDialog.value = true
        }
    ) {
        Text(dateResult)
    }

    if (openDialog.value) {
        val datePickerState = rememberDatePickerState()
        val confirmEnabled = derivedStateOf { datePickerState.displayMode != null }
        DatePickerDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                        var date = "no selection"
                        if (datePickerState.selectedDateMillis != null) {
                            onDateChange(datePickerState.selectedDateMillis!!)
                            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
                            date = simpleDateFormat.format(datePickerState.selectedDateMillis)
                            //date = Tools.convertLongToTime(datePickerState.selectedDateMillis!!)
                        }
                        dateResult = date
                    },
                    enabled = confirmEnabled.value
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}

@Composable
fun AddUserDialog(
    onDismissRequest: () -> Unit,
    onUserChange: (String) -> Unit,
    value: String
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp, 4.dp),
                    value = value,
                    onValueChange = {
                        Log.i("Komols amount", it.toString())
                        onUserChange(it)
                    },
                    label = {
                        Text(text = "Name")
                    }
                )

                Row (modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp, 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                    ) {
                    Button(onClick = {onDismissRequest()}) {
                        Text("Cancel",
                            color = Color.Red
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(onClick = {
                        //TODO : Add new user add logics
                    }) {
                        Text("Add",
                            color = Color.Green,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}