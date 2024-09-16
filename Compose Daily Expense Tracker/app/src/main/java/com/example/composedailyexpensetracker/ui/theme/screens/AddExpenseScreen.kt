package com.example.composedailyexpensetracker.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composedailyexpensetracker.viewmodel.ExpenseViewModel
import java.text.SimpleDateFormat

@Composable
fun AddExpenseScreen(navController: NavController, expenseViewModel: ExpenseViewModel) {
    var expenseAmount by remember { mutableStateOf("") }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            DateSelector()
            UserBox()
            OutlinedTextField(
                modifier= Modifier.fillMaxWidth().padding(32.dp, 4.dp),
                value = expenseAmount,
                onValueChange = {
                    expenseAmount = it
                },
                label = {
                    Text(text = "Amount in BDT")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Button(onClick = {
                /*viewModel.addTodo(inputText)
                inputText = ""*/
            },
                Modifier.fillMaxWidth().padding(32.dp, 4.dp)) {
                Text(text = "Add")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserBox() {
    val users = arrayOf("Komol", "Sukanta")
    var isExpanded by remember { mutableStateOf(false) }
    var selectedUser by remember { mutableStateOf(users[0]) }

    Box(
        modifier = Modifier.fillMaxWidth().padding(32.dp, 4.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = {
                isExpanded = !isExpanded
            }
        ) {
            TextField(
                value = selectedUser,
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
                            selectedUser = user
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
fun DateSelector() {
    // Decoupled snackbar host state from scaffold state for demo purposes.
    var dateResult by remember { mutableStateOf("Select date") }
    val openDialog = remember { mutableStateOf(false) }

    OutlinedButton(
        modifier = Modifier.fillMaxWidth().padding(32.dp, 4.dp),
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
                        if(datePickerState.selectedDateMillis != null){
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