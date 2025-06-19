package com.example.refill3.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.refill3.presentation.components.SignUpScreen
import com.example.refill3.presentation.screens.auth.LoginScreen
import com.example.refill3.presentation.components.ForgotPassword
import com.example.refill3.presentation.screens.apiscreens.ApiDashboard
import com.example.refill3.presentation.screens.dashboard.DashboardScreen

// INSIDE THIS FILE WE WILL DEFINE NAVCONTROLLER : THIS IS USED TO NAVIGATE
// TO DIFFERENT COMPOSABLES / SCREENS
@Composable
fun TodoNavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = "signUp") {
        composable("signUp"){
            SignUpScreen(navController)
        }
        composable("login"){
            LoginScreen(navController)
        }
        composable("dashboard") {
            DashboardScreen(
                //properties for the composable
                navController
            )
        }
        composable("forgotpassword") {
            ForgotPassword(
                navController
            )
        }

        composable("apiroute"){
            ApiDashboard(
                navController
            )
        }


        // here will define the addtoDo composable

    }

}
