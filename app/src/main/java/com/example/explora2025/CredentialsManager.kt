// Put your package name here. Check your activity for reference.
package com.example.explora2025

class CredentialsManager {
    fun isEmailValid(email: String): Boolean {
        if (email.isEmpty()) return false
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()
        return emailRegex.matches(email)
    }
    fun isPasswordValid(password: String) : Boolean {
        return password.isNotEmpty()
    }
}