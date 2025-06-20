//package com.example.refill3.presentation.screens.auth
package com.example.refill3.presentation.screens

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

@Composable
fun ForgotPassword(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    Column {
        Text("Forgot Password")
        TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        Button(onClick = {
            // Placeholder: Replace with actual password reset logic (e.g., Firebase)
            if (email.isNotEmpty()) {
                // Simulate sending reset email
                navController.navigate("login")
            }
        }) {
            Text("Reset Password")
        }
        Button(onClick = { navController.navigate("login") }) {
            Text("Back to Login")
        }
    }
}
