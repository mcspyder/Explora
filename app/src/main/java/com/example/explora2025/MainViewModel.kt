package com.example.explora2025
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import kotlinx.coroutines.Job
data class CatBreedsUiState(
    val catBreeds: List<CatBreed> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Boolean = false
)
class MainViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CatBreedsUiState(catBreeds = loadInitialBreeds()))
    val uiState: StateFlow<CatBreedsUiState> = _uiState
    private var searchJob: Job? = null
    private fun loadInitialBreeds(): List<CatBreed> {
        return listOf(
            CatBreed(1, "Siamese", R.drawable.siamese, "Elegant and affectionate. Relaxed and chill.", "Siamese cats are known for being highly intelligent, curious, and active. They are often described as being \"dog-like\" in their behavior, as they enjoy playing fetch, following their owners around, and walking on a leash. Many Siamese cats even enjoy swimming or playing in the water."),
            CatBreed(2, "Persian", R.drawable.persian, "Charming and fluffy. Always look miserable and exhausted.", "With their snub noses, chubby cheeks, and long hair, the Persian cat is quite an exquisite breed. They're also typically quiet and affectionate cats who enjoy being held, but they're content just lounging around too. They make a perfect, purring lap warmer!"),
            CatBreed(3, "Oggy", R.drawable.oggy, "My cat Oggy. Won't stop beating his brother Puszkin up.", "The Scottish fold is a rare breed of cat which came about due to a genetic mutation in the 1960s. They are known for their rounded faces and bodies and unique folds on their ears, which give them what is often referred to as an ‘owl-like’ appearance. Their coats can range in colour from white to black or anything in-between, and can be with markings or solid colours. The long-haired version of the Scottish fold cat is known as a Highland Fold. There has been speculation over the years that these unusually gorgeous cats actually have ancestry from the Orient. This is because a 1796 issue of the Universal Magazine of Knowledge and Pleasure described wild folded-ear cats in China. ")
        )
    }
    fun onSearchQueryChanged(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, searchQuery = query) }
            delay(300)
            val filteredBreeds = if (query.length <= 2) {
                loadInitialBreeds()
            } else {
                loadInitialBreeds().filter {
                    it.name.contains(query, ignoreCase = true) || it.description.contains(query, ignoreCase = true)
                }
            }
            delay(2000)
            _uiState.update { it.copy(catBreeds = filteredBreeds, isLoading = false) }
        }
    }
}
