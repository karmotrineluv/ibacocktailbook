package com.example.ibacocktailbook.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ibacocktailbook.db.CocktailRepository
import com.example.ibacocktailbook.db.CocktailWithIngredients
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: CocktailRepository
) : ViewModel() {

    // Актуальный список всех коктейлей
    val allCocktails: LiveData<List<CocktailWithIngredients>> = repository.allCocktails

    private val _randomCocktail = MutableLiveData<CocktailWithIngredients?>()
    val randomCocktail: LiveData<CocktailWithIngredients?> = _randomCocktail

    fun loadRandomCocktail() {
        viewModelScope.launch {
            _randomCocktail.value = repository.getRandomCocktail()
        }
    }

    fun toggleFavorite(cocktail: CocktailWithIngredients) {
        viewModelScope.launch {
            val updatedCocktail = cocktail.cocktail.copy(
                isFavorite = !cocktail.cocktail.isFavorite
            )
            repository.updateCocktail(updatedCocktail)
        }
    }
}
