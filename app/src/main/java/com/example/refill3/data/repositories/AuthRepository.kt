package com.example.refill3.data.repositories

object AuthRepository {
    private val users = mutableMapOf<String, String>() // email to password
    fun signUp(email: String, password: String): Boolean {
        if (users.containsKey(email)) return false
        users[email] = password
        return true
    }
    fun login(email: String, password: String): Boolean {
        return users[email] == password
    }
}
