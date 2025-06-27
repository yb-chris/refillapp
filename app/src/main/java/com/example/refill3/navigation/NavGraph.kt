package com.example.refill3.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.refill3.presentation.screens.ConfirmationScreen
import com.example.refill3.presentation.screens.auth.LoginScreen
import com.example.refill3.presentation.screens.auth.SignUpScreen
import com.example.refill3.presentation.screens.DashboardScreen
import com.example.refill3.presentation.screens.OrderScreen
import com.example.refill3.presentation.screens.ForgotPassword
import com.example.refill3.presentation.screens.HomeScreen
import com.example.refill3.presentation.screens.PaymentScreen
import com.example.refill3.presentation.screens.SummaryScreen

@Composable
fun RefillNavGraph(navController: NavHostController) {
    val startDestination = if (isUserLoggedIn()) "dashboard" else "login"
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = "signup") {
            SignUpScreen(navController)
        }
        composable(route = "login") {
            LoginScreen(navController)
        }
        composable(route = "dashboard") {
            DashboardScreen(navController)
        }
        composable(route = "order") {
            OrderScreen(navController)
        }
        composable(route = "forgotpassword") {
            ForgotPassword(navController)
        }
        composable(route = "home") {
            HomeScreen(navController, viewModel())
        }
        composable(route = "order") {
            OrderScreen(navController, viewModel())
        }
        composable(route = "payment") {
            PaymentScreen(navController, viewModel())
        }
        composable(route = "summary") {
            SummaryScreen(navController)
        }
        composable(route = "confirmation") {
            ConfirmationScreen(navController)
        }
        composable(route = "dashboard") {
            DashboardScreen(navController)
        }


    }
}

fun isUserLoggedIn(): Boolean {
    // Placeholder: Replace with real auth check (e.g., Firebase)
    return false
}
//package com.example.refill3.navigation
//
//import androidx.compose.runtime.Composable
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import com.example.refill3.presentation.screens.SignUpScreen
//import com.example.refill3.presentation.screens.LoginScreen
//import com.example.refill3.presentation.screens.DashboardScreen
//import com.example.refill3.presentation.screens.ForgotPassword
//
//@Composable
//fun RefillNavGraph(navController: NavHostController) {
//    NavHost(navController = navController, startDestination = "signup") {
//        composable(route = "signup") {
//            SignUpScreen(navController)
//        }
//        composable(route = "login") {
//            LoginScreen(navController)
//        }
//        composable(route = "dashboard") {
//            DashboardScreen(navController)
//        }
//        composable(route = "forgotpassword") {
//            ForgotPassword(navController)
//        }
//    }
//}.............alt
