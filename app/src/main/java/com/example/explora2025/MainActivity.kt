package com.example.explora2025
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton


class MainActivity : AppCompatActivity() {
    private var isFragmentA = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize with FragmentA
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, FragmentA())
            .commit()

        findViewById<MaterialButton>(R.id.switch_button).setOnClickListener {
            val fragment: Fragment = if (isFragmentA) FragmentB() else FragmentA()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
            isFragmentA = !isFragmentA
        }
    }
}