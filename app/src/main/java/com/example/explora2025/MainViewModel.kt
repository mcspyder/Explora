package com.example.explora2025

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {
    private val _searchState = MutableStateFlow(CatBreedSearchState())
    val searchState: StateFlow<CatBreedSearchState> = _searchState

    private val allCatBreeds = listOf(
        CatBreed(1, "Siamese", R.drawable.siamese, "Elegant and affectionate. Relaxed and chill.", "Siamese cats are known for being highly intelligent, curious, and active..."),
        CatBreed(2, "Persian", R.drawable.persian, "Charming and fluffy. Always look miserable and exhausted.", "With their snub noses, chubby cheeks, and long hair..."),
        CatBreed(3, "Oggy", R.drawable.oggy, "My cat Oggy. Won't stop beating his brother Puszkin up.", "The Scottish fold is a rare breed of cat...")
    )

    init {
        _searchState.update { it.copy(catBreeds = allCatBreeds) }
    }

    fun onSearchQueryChanged(query: String) {
        if (query.length <= 2) {
            // show all cat breeds if query is empty or too short
            _searchState.update { it.copy(
                searchQuery = query,
                catBreeds = allCatBreeds
            )}
            return
        }

        val filteredCatBreeds = allCatBreeds.filter { catBreed ->
            catBreed.name.contains(query, ignoreCase = true) ||
                    catBreed.description.contains(query, ignoreCase = true)
        }

        _searchState.update { it.copy(
            searchQuery = query,
            catBreeds = filteredCatBreeds
        )}
    }
}