package com.example.explora2025

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.textfield.TextInputLayout
class RegisterFragment : Fragment() {
    private val credentialsManager by lazy { (requireActivity().application as ExploraApplication).credentialsManager }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_create_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val emailInputLayout = view.findViewById<TextInputLayout>(R.id.email_layout)
        val passwordInputLayout = view.findViewById<TextInputLayout>(R.id.password_layout)
        val termsCheckbox = view.findViewById<MaterialCheckBox>(R.id.terms_checkbox)

        view.findViewById<View>(R.id.next_button).setOnClickListener {
            val email = emailInputLayout.editText?.text.toString()
            val password = passwordInputLayout.editText?.text.toString()
            if (!termsCheckbox.isChecked) {
                Toast.makeText(context, "Please agree to our terms and conditions.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!credentialsManager.isEmailValid(email)) {
                emailInputLayout.error = "Invalid email format!"
                return@setOnClickListener
            }
            if (!credentialsManager.isPasswordValid(password)) {
                passwordInputLayout.error = "Password cannot be empty."
                return@setOnClickListener
            }
            val result = credentialsManager.register(email, password)
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
            if (result.contains("successful")) {
                (requireActivity() as AuthActivity).replaceFragment(LoginFragment(), true)
            }
        }
        view.findViewById<View>(R.id.login_text).setOnClickListener {
            (requireActivity() as AuthActivity).replaceFragment(LoginFragment(), true)
        }
    }
}
