package com.example.refill3.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.refill3.presentation.viewmodel.OrderViewModel

@Composable
fun SummaryScreen(navController: NavController, orderViewModel: OrderViewModel) {
    val price = orderViewModel.liters * 10 // 10 KES per liter

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Order Summary",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text("Water Amount: ${orderViewModel.liters} Liters")
        Text("Total Price: KES $price")

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.weight(1f)
            ) {
                Text("Back")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = { navController.navigate("payment") },
                modifier = Modifier.weight(1f)
            ) {
                Text("Proceed to Payment")
            }
        }
    }
}