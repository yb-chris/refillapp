package com.example.refill3.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.refill3.presentation.screens.auth.LoginScreen
import com.example.refill3.presentation.screens.auth.SignUpScreen
import com.example.refill3.presentation.screens.DashboardScreen
import com.example.refill3.presentation.screens.OrderScreen
import com.example.refill3.presentation.screens.ForgotPassword

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
