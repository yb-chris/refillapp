package com.example.refill3.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.refill3.presentation.viewmodel.OrderViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun OrderScreen(navController: NavHostController, viewModel: OrderViewModel = viewModel()) {
    var liters by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(value = liters, onValueChange = { liters = it },
            label = { Text("Liters") })
        TextField(value = quantity, onValueChange = { quantity = it },
            label = { Text("Quantity") })
        Button(onClick = {
            val litersInt = liters.toIntOrNull() ?: 0
            val quantityInt = quantity.toIntOrNull() ?: 0
            val totalPrice = litersInt * quantityInt * 0.5 // Example pricing
            viewModel.createOrder(litersInt, quantityInt, totalPrice,
                FirebaseAuth.getInstance().currentUser?.uid ?: "")
            navController.navigate("payment")
        }) {
            Text("Place Order")
        }
    }
}