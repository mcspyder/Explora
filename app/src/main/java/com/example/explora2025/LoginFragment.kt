package com.example.explora2025

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
class LoginFragment : Fragment() {
    private lateinit var credentialsManager: CredentialsManager

    override fun onAttach(context: Context) {
        super.onAttach(context)
        credentialsManager = (activity as AuthActivity).credentialsManager
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_login, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val emailInputLayout = view.findViewById<TextInputLayout>(R.id.email_layout)
        val passwordInputLayout = view.findViewById<TextInputLayout>(R.id.password_layout)
        view.findViewById<View>(R.id.next_button).setOnClickListener {
            val email = emailInputLayout.editText?.text.toString()
            val password = passwordInputLayout.editText?.text.toString()

            if (credentialsManager.authenticate(email, password)) {
                Toast.makeText(context, "Welcome back!", Toast.LENGTH_SHORT).show()
                val intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
                requireActivity().finish()

            } else {
                Toast.makeText(context, "Invalid credentials. Try again.", Toast.LENGTH_SHORT).show()
            }
        }
        view.findViewById<View>(R.id.register_text).setOnClickListener {
            (activity as AuthActivity).replaceFragment(RegisterFragment(), addToBackStack = true)
        }
    }
}
