// Put your package name here. Check your activity for reference.
package com.example.explora2025

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
class CredentialsManager {
    private val accounts = mutableMapOf<String, String>()
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    fun isEmailValid(email: String): Boolean {
        if (email.isEmpty()) return false
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()
        return emailRegex.matches(email)
    }

    fun isPasswordValid(password: String): Boolean {
        return password.isNotEmpty()
    }

    fun register(email: String, password: String): String {
        val normalizedEmail = email.lowercase()
        if (accounts.containsKey(normalizedEmail)) {
            return "Error!! Email is already registered."
        }
        accounts[normalizedEmail] = password
        return "Registration is successful! :)"
    }

    fun authenticate(email: String, password: String): Boolean {
        val normalizedEmail = email.lowercase()
        val isAuthenticated = accounts[normalizedEmail] == password
        if (isAuthenticated) {
            _isLoggedIn.value = true
        }
        return isAuthenticated
    }

    fun logout() {
        _isLoggedIn.value = false
    }
}
