package com.example.explora2025

data class CatBreedSearchState(
    val catBreeds: List<CatBreed> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Boolean = false
)
