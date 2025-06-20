package com.example.refill3.presentation.screens.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import com.example.refill3.data.repositories.AuthRepository

@Composable
fun LoginScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column {
        TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        TextField(value = password, onValueChange = { password = it }, label = { Text("Password") })
        Button(onClick = {
            if (AuthRepository.login(email, password)) {
                navController.navigate("dashboard")
            } else {
                // Add error message
            }
        }) {
            Text("Login")
        }
        Button(onClick = { navController.navigate("signup") }) {
            Text("Go to Sign Up")
        }
    }
}
