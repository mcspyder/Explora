package com.example.explora2025

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import android.content.Intent
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.progressindicator.CircularProgressIndicator
import android.widget.Button
class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private val viewModel: MainViewModel by viewModels()
    private lateinit var credentialsManager: CredentialsManager
    private lateinit var progressIndicator: CircularProgressIndicator
    private lateinit var adapter: CatBreedAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        credentialsManager = (application as ExploraApplication).credentialsManager

        setupViews()
        setupRecyclerView()
        setupSearchView()
        observeViewModel()
        observeLoginState()
    }
    private fun setupViews() {
        progressIndicator = findViewById(R.id.progress_indicator)
        findViewById<Button>(R.id.logout_button).setOnClickListener {
            credentialsManager.logout()
            Toast.makeText(this, "Logged out successfully!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
        }
    }
    private fun setupRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CatBreedAdapter(
            onItemClick = { catBreed ->
                val intent = Intent(this, CatDetailsActivity::class.java).apply {
                    putExtra("imageResId", catBreed.imageResId)
                    putExtra("name", catBreed.name)
                    putExtra("extraDetails", catBreed.extraDetails)
                }
                startActivity(intent)
            },
            onLikeClick = { catBreed ->
                Toast.makeText(this, "Liked: ${catBreed.name}", Toast.LENGTH_SHORT).show()
            },
            onShareClick = { catBreed ->
                Toast.makeText(this, "Shared: ${catBreed.name}", Toast.LENGTH_SHORT).show()
            }
        )
        recyclerView.adapter = adapter
    }
    private fun setupSearchView() {
        val searchView = findViewById<SearchView>(R.id.search_view)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = true
            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.onSearchQueryChanged(newText ?: "")
                return true
            }
        })
    }
    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                progressIndicator.isVisible = state.isLoading
                recyclerView.isVisible = !state.isLoading
                adapter.submitList(state.catBreeds)
            }
        }
    }
    private fun observeLoginState() {
        lifecycleScope.launch {
            credentialsManager.isLoggedIn.collect { isLoggedIn ->
                if (!isLoggedIn) {
                    Toast.makeText(this@MainActivity, "Session expired. Please log in again.", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@MainActivity, AuthActivity::class.java))
                    finish()
                }
            }
        }
    }
}
