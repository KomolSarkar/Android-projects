package com.example.composedailyexpensetracker.ui.theme.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Total cost: BDT 4000.00",
                color = Color.Red,
                fontSize = 24.sp,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.heightIn(8.dp))

            Column {
                PerPersonCalculation()
                Spacer(modifier = Modifier.heightIn(4.dp))
                PerPersonCalculation()
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

            Column {
                Expense()
                Spacer(modifier = Modifier.heightIn(2.dp))
                Expense()
                Spacer(modifier = Modifier.heightIn(2.dp))
                Expense()
                Spacer(modifier = Modifier.heightIn(2.dp))
                Expense()
                Spacer(modifier = Modifier.heightIn(2.dp))
                Expense()
                Spacer(modifier = Modifier.heightIn(2.dp))
                Expense()
                Spacer(modifier = Modifier.heightIn(2.dp))
                Expense()
                Spacer(modifier = Modifier.heightIn(2.dp))
                Expense()
                Spacer(modifier = Modifier.heightIn(2.dp))
            }
        }
    }
}

@Composable
fun PerPersonCalculation() {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.weight(.4f)
                .align(Alignment.CenterVertically),
            text = "Komol",
            textAlign = TextAlign.Left
        )

        Text(
            modifier = Modifier.weight(.3f)
                .align(Alignment.CenterVertically),
            text = "BDT 2000.00",
            textAlign = TextAlign.Left
        )

        Text(
            modifier = Modifier.weight(.3f)
                .align(Alignment.CenterVertically),
            text = "BDT 0.00",
            textAlign = TextAlign.Left
        )
    }
}

@Composable
fun Expense() {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.weight(.3f)
                .align(Alignment.CenterVertically),
            text = "14/09/2024",
            textAlign = TextAlign.Left
        )

        Text(
            modifier = Modifier.weight(.4f)
                .align(Alignment.CenterVertically),
            text = "Komol",
            textAlign = TextAlign.Left
        )

        Text(
            modifier = Modifier.weight(.3f)
                .align(Alignment.CenterVertically),
            text = "BDT 100.00",
            textAlign = TextAlign.Left
        )
    }
}