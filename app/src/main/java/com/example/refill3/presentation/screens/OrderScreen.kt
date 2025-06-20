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
import com.example.refill3.data.models.WaterOrder
import com.example.refill3.data.repositories.WaterOrderRepository
import kotlin.random.Random

@Composable
fun OrderScreen(navController: NavHostController) {
    var amount by remember { mutableStateOf("") }
    Column {
        TextField(value = amount, onValueChange = { amount = it }, label = { Text("Amount (bottles)") })
        Button(onClick = {
            val order = WaterOrder(id = Random.nextInt(), amount = amount.toIntOrNull() ?: 0, userId = "loggedInUser")
            WaterOrderRepository.createOrder(order)
            navController.navigate("dashboard")
        }) {
            Text("Place Order")
        }
    }
}
