package com.example.refill3.data.repositories

import com.example.refill3.data.models.WaterOrder
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class WaterOrderRepository {
    private val db = FirebaseFirestore.getInstance()
    private val ordersCollection = db.collection("orders")

    suspend fun createOrder(order: WaterOrder): String? {
        return try {
            val document = ordersCollection.add(order).await()
            document.id
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getOrders(userId: String): List<WaterOrder> {
        return try {
            ordersCollection.whereEqualTo("userId", userId).get().await()
                .toObjects(WaterOrder::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun updateOrder(order: WaterOrder): Boolean {
        return try {
            ordersCollection.document(order.id).set(order).await()
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun deleteOrder(orderId: String): Boolean {
        return try {
            ordersCollection.document(orderId).delete().await()
            true
        } catch (e: Exception) {
            false
        }
    }

//    companion object {
//       fun getOrderCount(): Int {
////           if (someCondition) {
//               return 10
//           }
//            return 0 // Default return value
//        }
//    }
//    companion object {
//    fun getOrders(status: String): Any {
//
//    }
//}
}//remove this (not necessary)