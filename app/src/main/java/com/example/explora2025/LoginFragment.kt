package com.example.explora2025

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout

class LoginFragment : Fragment() {
    private val credentialsManager by lazy { (requireActivity().application as ExploraApplication).credentialsManager }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.register_text).setOnClickListener {
            (requireActivity() as AuthActivity).replaceFragment(RegisterFragment(), true)
        }
        view.findViewById<View>(R.id.next_button).setOnClickListener {
            validateAndLogin(view)
        }
    }

    private fun validateAndLogin(view: View) {
        val emailInputLayout = view.findViewById<TextInputLayout>(R.id.email_layout)
        val passwordInputLayout = view.findViewById<TextInputLayout>(R.id.password_layout)

        val email = emailInputLayout.editText?.text.toString()
        val password = passwordInputLayout.editText?.text.toString()

        val isEmailValid = credentialsManager.isEmailValid(email)
        val isPasswordValid = credentialsManager.isPasswordValid(password)

        if (!isEmailValid) {
            emailInputLayout.error = "Invalid form of email. Please try again."
        } else {
            emailInputLayout.error = null
        }

        if (!isPasswordValid) {
            passwordInputLayout.error = "Password cannot be empty. Please enter one."
        } else {
            passwordInputLayout.error = null
        }

        if (isEmailValid && isPasswordValid) {
            if (credentialsManager.authenticate(email, password)) {
                Toast.makeText(requireContext(), "Welcome back!", Toast.LENGTH_SHORT).show()
                val intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
                requireActivity().finish() // Close the current activity
            } else {
                Toast.makeText(requireContext(), "Invalid credentials!", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
