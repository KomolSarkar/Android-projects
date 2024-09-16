package com.example.composenavigationdemoapp.ui.theme

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationController() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "screenA", builder = {
        composable("screenA") {
            ScreenA(navController)
        }
        composable("screenB") {
            ScreenB()
        }
    })
}