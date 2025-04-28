// HomeViewModel.kt
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

    // Используем актуальный LiveData из репозитория
    val allCocktails: LiveData<List<CocktailWithIngredients>> = repository.allCocktails

    private val _randomCocktail = MutableLiveData<CocktailWithIngredients?>()
    val randomCocktail: LiveData<CocktailWithIngredients?> = _randomCocktail

    fun loadRandomCocktail() {
        viewModelScope.launch {
            _randomCocktail.value = repository.getRandomCocktail()
        }
    }
}