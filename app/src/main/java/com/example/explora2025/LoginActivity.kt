package com.example.explora2025

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    //custom getters
    private val emailInputLayout: TextInputLayout
        get() = findViewById(R.id.email_layout)

    private val passwordInputLayout: TextInputLayout
        get() = findViewById(R.id.password_layout)

    private val registerTextView: TextView
        get() = findViewById(R.id.register_text)

    private val credentialsManager = CredentialsManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        registerTextView.setOnClickListener {
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
        }
        findViewById<com.google.android.material.button.MaterialButton>(R.id.next_button).setOnClickListener {
            validateAndLogin()
        }
    }

    override fun onBackPressed() {
        finishAffinity()
    }

    private fun validateAndLogin() {
        val email = emailInputLayout.editText?.text.toString()
        val password = passwordInputLayout.editText?.text.toString()
        val isEmailValid = checkEmail(email)
        val isPasswordValid = checkPassword(password)

        if (isEmailValid && isPasswordValid) {
            showSuccessAndGoHome()
        }
    }
    private fun checkEmail(email: String): Boolean {
        return if (!credentialsManager.isEmailValid(email)) {
            emailInputLayout.error = "Invalid form of email. Please try again."
            false
        } else {
            emailInputLayout.error = null
            true
        }
    }
    private fun checkPassword(password: String): Boolean {
        return if (!credentialsManager.isPasswordValid(password)) {
            passwordInputLayout.error = "Password cannot be empty. Please enter one."
            false
        } else {
            passwordInputLayout.error = null
            true
        }
    }
    private fun showSuccessAndGoHome() {
        Toast.makeText(this, "Welcome back!", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}
