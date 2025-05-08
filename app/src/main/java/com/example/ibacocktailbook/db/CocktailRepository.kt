package com.example.ibacocktailbook.db

import android.util.Log
import androidx.lifecycle.LiveData

class CocktailRepository(private val cocktailDao: CocktailDao) {

    val allCocktails: LiveData<List<CocktailWithIngredients>> = cocktailDao.getAllCocktails()

    suspend fun insertCocktail(cocktail: CocktailEntity, ingredients: List<IngredientEntity>) {
        Log.d("CocktailRepository", "Inserting cocktail: ${cocktail.name}")
        cocktailDao.insertCocktail(cocktail, ingredients)
    }

    suspend fun getRandomCocktail(): CocktailWithIngredients? {
        return cocktailDao.getRandomCocktail()
    }

    suspend fun searchCocktailsByName(query: String): List<CocktailWithIngredients> {
        return cocktailDao.searchCocktailsByName(query)
    }

    suspend fun getCocktailById(id: Int): CocktailWithIngredients? {
        return cocktailDao.getCocktailById(id)
    }

    suspend fun deleteCocktail(cocktail: CocktailEntity) {
        cocktailDao.deleteIngredientsByCocktailId(cocktail.id)
        cocktailDao.deleteCocktail(cocktail)
    }

    suspend fun updateCocktail(cocktail: CocktailEntity) {
        cocktailDao.updateCocktail(cocktail)
    }

}
