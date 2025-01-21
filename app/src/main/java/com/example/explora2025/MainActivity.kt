package com.example.explora2025

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.content.Intent
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: CatBreedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
        setupSearchView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = CatBreedAdapter(
            emptyList(),
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
        searchView = findViewById(R.id.search_view)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.onSearchQueryChanged(newText ?: "")
                return true
            }
        })
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.searchState.collect { state ->
                adapter = CatBreedAdapter(
                    state.catBreeds,
                    onItemClick = { catBreed ->
                        val intent = Intent(this@MainActivity, CatDetailsActivity::class.java).apply {
                            putExtra("imageResId", catBreed.imageResId)
                            putExtra("name", catBreed.name)
                            putExtra("extraDetails", catBreed.extraDetails)
                        }
                        startActivity(intent)
                    },
                    onLikeClick = { catBreed ->
                        Toast.makeText(this@MainActivity, "Liked: ${catBreed.name}", Toast.LENGTH_SHORT).show()
                    },
                    onShareClick = { catBreed ->
                        Toast.makeText(this@MainActivity, "Shared: ${catBreed.name}", Toast.LENGTH_SHORT).show()
                    }
                )
                recyclerView.adapter = adapter
            }
        }
    }
}