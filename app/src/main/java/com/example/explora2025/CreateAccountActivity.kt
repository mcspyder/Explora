package com.example.explora2025

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

class CreateAccountActivity : AppCompatActivity() {
    private val credentialsManager = CredentialsManager()
    //getters
    private val emailInputLayout: TextInputLayout
        get() = findViewById(R.id.email_layout)

    private val passwordInputLayout: TextInputLayout
        get() = findViewById(R.id.password_layout)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        val loginTextView: TextView = findViewById(R.id.login_text)
        loginTextView.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }

        findViewById<com.google.android.material.button.MaterialButton>(R.id.next_button).setOnClickListener {
            handleRegistration()
        }
    }

    private fun handleRegistration() {
        val email = emailInputLayout.editText?.text.toString()
        val password = passwordInputLayout.editText?.text.toString()


        if (!credentialsManager.isEmailValid(email)) {
            emailInputLayout.error = "Invalid email format. Please try again."
            return
        } else {
            emailInputLayout.error = null
        }

        if (!credentialsManager.isPasswordValid(password)) {
            passwordInputLayout.error = "Password cannot be empty."
            return
        } else {
            passwordInputLayout.error = null
        }

        val result = credentialsManager.register(email, password)
        if (result == "Registration successful.") {
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
        }
    }
}
