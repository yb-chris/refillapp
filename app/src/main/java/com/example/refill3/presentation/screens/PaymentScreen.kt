package com.example.refill3.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.refill3.presentation.viewmodel.OrderViewModel


@Composable
fun PaymentScreen(navController: NavController, orderViewModel: OrderViewModel) {
    var phoneNumber by remember { mutableStateOf("") }
    val amount = orderViewModel.liters * 10 // Placeholder, replace with actual pricing logic

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Payment via M-Pesa", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(bottom = 16.dp))
        Text(text = "Amount to Pay: KES $amount")

        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text(text = "Phone Number (e.g., 07XX XXX XXX)") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )

        Button(onClick = {
            // Replace with actual M-Pesa API call
            viewModelScope.launch {
                try {
                    // Example: Simulate M-Pesa STK Push API call
                    val response = mpesaService.initiateStkPush(phoneNumber, amount.toInt())
                    if (response.isSuccessful) {
                        orderViewModel.paymentStatus = "Success"
                        navController.navigate("summary") {
                            popUpTo(route = "home") { inclusive = true }
                        }
                    } else {
                        orderViewModel.paymentStatus = "Failed"
                    }
                } catch (e: Exception) {
                    orderViewModel.paymentStatus = "Error: ${e.message}"
                }
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Place Order")
        }
    }
}


//
//@Composable
//fun PaymentScreen(navController: NavController, orderViewModel: OrderViewModel) {
//    var phoneNumber by remember { mutableStateOf("") }
//    val amount = orderViewModel.liters * 10
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(
//            text = "Payment via M-Pesa",
//            style = MaterialTheme.typography.titleLarge,
//            modifier = Modifier.padding(bottom = 16.dp)
//        )
//        Text("Amount to Pay: KES $amount")
//
//        OutlinedTextField(
//            value = phoneNumber,
//            onValueChange = { phoneNumber = it },
//            label = { Text("Phone Number (e.g., 07XX XXX XXX)") },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(vertical = 16.dp)
//        )
//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = 16.dp),
//            horizontalArrangement = Arrangement.SpaceEvenly
//        ) {
//            Button(
//                onClick = { navController.popBackStack() },
//                modifier = Modifier.weight(1f)
//            ) {
//                Text("Back")
//            }
//            Spacer(modifier = Modifier.width(16.dp))
//            Button(
//                onClick = {
//                    if (phoneNumber.length == 10) {
//                        orderViewModel.paymentStatus = "paid"
//                        navController.navigate("confirmation")
//                    }
//                },
//                modifier = Modifier.weight(1f)
//            ) {
//                Text("Confirm Payment")
//            }
//        }
//    }
//}