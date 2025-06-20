package com.example.refill3.data.repositories

import com.example.refill3.data.models.WaterOrder

object WaterOrderRepository {
    private val orders = mutableListOf<WaterOrder>()
    fun createOrder(order: WaterOrder) = orders.add(order)
    fun getOrders(userId: String) = orders.filter { it.userId == userId }
    fun updateOrder(updatedOrder: WaterOrder) {
        val index = orders.indexOfFirst { it.id == updatedOrder.id }
        if (index != -1) orders[index] = updatedOrder
    }
    fun deleteOrder(id: Int) = orders.removeIf { it.id == id }
}
