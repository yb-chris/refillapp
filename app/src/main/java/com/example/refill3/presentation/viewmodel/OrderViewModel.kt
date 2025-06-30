package com.example.refill3.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class OrderItem(val id: String, val name: String, val price: Double, var quantity: Int,
                     val liters:Double=0.0,val paymentStatus:String="")

class OrderViewModel : ViewModel() {

    private val _paymentStatus = MutableStateFlow("")
    val paymentStatus: StateFlow<String> = _paymentStatus.asStateFlow()
//    private val _paymentStatus=MutableStateFlow()
//   var paymentStatus: String

//    private val _liters = MutableStateFlow(0.0)
//    var liters: MutableStateFlow<Double> =_liters

    private val _orderItems = MutableStateFlow<List<OrderItem>>(emptyList())
    val orderItems: StateFlow<List<OrderItem>> = _orderItems

    private val _totalPrice = MutableStateFlow(0.0)
    val totalPrice: StateFlow<Double> = _totalPrice

    private val _liters = MutableStateFlow(5)
    val liters: StateFlow<Int> = _liters.asStateFlow() // Line 14 adjusted

    fun placeOrder() {
        _liters.value = 10 // Example update
    }

    fun addItem(item: OrderItem) {
        val currentItems = _orderItems.value.toMutableList()
        val existingItem = currentItems.find { it.id == item.id }
        if (existingItem != null) {
            existingItem.quantity += item.quantity
        } else {
            currentItems.add(item)
        }
        _orderItems.value = currentItems
        calculateTotal()
    }

    fun removeItem(itemId: String) {
        val currentItems = _orderItems.value.toMutableList()
        currentItems.removeAll { it.id == itemId }
        _orderItems.value = currentItems
        calculateTotal()
    }

    fun updateQuantity(itemId: String, newQuantity: Int) {
        val currentItems = _orderItems.value.toMutableList()
        val item = currentItems.find { it.id == itemId }
        item?.let {
            it.quantity = newQuantity
            _orderItems.value = currentItems
            calculateTotal()
        }
    }

    private fun calculateTotal() {
        val total = _orderItems.value.sumOf { it.price * it.quantity }
        _totalPrice.value = total
    }

    fun submitOrder(onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        viewModelScope.launch {
            try {
                // Simulate API call (replace with actual repository call)
                onSuccess()
            } catch (e: Exception) {
                onFailure(e.message ?: "Unknown error")
            }
        }
    }

    fun clearOrder() {
        _orderItems.value = emptyList()
        _totalPrice.value = 0.0
    }

    fun createOrder(litersInt: Int, quantityInt: Int, totalPrice: Double, s: String) {

    }
}
