package com.example.composenavigationdemoapp.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ScreenA(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "This is Screeen A", fontSize = 24.sp)
        Spacer(modifier = Modifier.heightIn(8.dp))
        Button(onClick = {
            navController.navigate("screenB")
        }) {
            Text(text = "Go to ScreenB")
        }
    }
}
