package com.example.refill3.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.refill3.data.repositories.WaterOrderRepository

@Composable
fun DashboardScreen(navController: NavHostController) {
    val orders = WaterOrderRepository.getOrders("loggedInUser")
    Column {
        Text("Your Orders")
        orders.forEach { order ->
            Text("Order ID: ${order.id}, Amount: ${order.amount} bottles")
        }
        Button(onClick = { navController.navigate("order") }) {
            Text("New Order")
        }
    }
}