package com.example.refill3.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class OrderItem(val id: String, val name: String, val price: Double, var quantity: Int,
                     val liters:Int)

class OrderViewModel : ViewModel() {
    var paymentStatus: String
    private val liters = MutableStateFlow<List<liters>>(emptyList())
    var liters: StateFlow<List<OrderItem>> =liters
//
//    private val
//    var paymentStatus: MutableStateFlow<List<payment>>

    private val _orderItems = MutableStateFlow<List<OrderItem>>(emptyList())
    val orderItems: StateFlow<List<OrderItem>> = _orderItems

    private val _totalPrice = MutableStateFlow(0.0)
    val totalPrice: StateFlow<Double> = _totalPrice

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