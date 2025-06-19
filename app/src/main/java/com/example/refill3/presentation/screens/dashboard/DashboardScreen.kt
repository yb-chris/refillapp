package com.example.refill3.presentation.screens.dashboard


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.refill3.model.Order
import com.example.refill3.viewmodel.OrderViewModel
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api)
@Composable
fun DashboardScreen(navController: NavHostController) {
    val viewModel: OrderViewModel = viewModel()
    val orders = viewModel.orders.collectAsState(initial = emptyList()).value

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Refill3") })
        },
        content = { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues).padding(16.dp)) {
                Text("Dashboard", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(16.dp))
                if (orders.isEmpty()) {
                    Text("You haven't placed any orders yet.")
                } else {
                    Text("Your recent orders:", style = MaterialTheme.typography.titleLarge)
                    LazyColumn {
                        items(orders.take(3)) { order ->
                            Card(modifier = Modifier.padding(8.dp)) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text("Order: ${order.amount} Liters")
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { navController.navigate("order_screen") }, modifier = Modifier.fillMaxWidth()) {
                    Text("Place New Refill Order")
                }
                if (orders.isNotEmpty()) {
                    val lastOrder = orders.last()
                    Button(onClick = {
                        viewModel.addOrder(Order(amount = lastOrder.amount))
                    }, modifier = Modifier.fillMaxWidth()) {
                        Text("Refill Last Order (${lastOrder.amount} Liters)")
                    }
                }
                Button(onClick = { navController.navigate("order_history") }, modifier = Modifier.fillMaxWidth()) {
                    Text("View All Orders")
                }
            }
        }
    )
}