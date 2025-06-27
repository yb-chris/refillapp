package com.example.refill3.data.models

data class WaterOrder(
    val id: String="",
    val liters:Int,
    val quantity: Int,
    val totalPrice: Double,
    val status: String,
    val amount: Int, // Number of bottles
    val userId: String,
    val timestamp: Long
)

