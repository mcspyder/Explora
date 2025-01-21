package com.example.explora2025

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CatDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cat_details)

        val catImage: ImageView = findViewById(R.id.cat_image)
        val catName: TextView = findViewById(R.id.cat_name)
        val catExtraDetails: TextView = findViewById(R.id.cat_extra_details)

        // Get the data from the intent
        val imageResId = intent.getIntExtra("imageResId", 0)
        val name = intent.getStringExtra("name") ?: "Unknown"
        val extraDetails = intent.getStringExtra("extraDetails") ?: "No additional details available."

        // Populate the views with data
        catImage.setImageResource(imageResId)
        catName.text = name
        catExtraDetails.text = extraDetails
    }
}
