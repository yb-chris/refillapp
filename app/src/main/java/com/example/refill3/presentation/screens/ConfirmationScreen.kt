package com.example.refill3.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.refill3.presentation.viewmodel.OrderViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth


@Composable
fun ConfirmationScreen(navController: NavController, orderViewModel: OrderViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Order Confirmed",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(text="Thank you! Your order of ${orderViewModel.liters} liters has been placed.")

        Button(
            onClick = {
//                orderViewModel.liters = 0.0
//                orderViewModel.paymentStatus = "pending"
                navController.navigate("home") {
                    popUpTo("home") { inclusive = true }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text("Place Another Order")
        }
    }
}