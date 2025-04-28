package com.example.ibacocktailbook.db

import androidx.lifecycle.LiveData

class CocktailRepository(private val cocktailDao: CocktailDao) {

    // Получаем все коктейли с ингредиентами
    val allCocktails: LiveData<List<CocktailWithIngredients>> = cocktailDao.getAllCocktails()

    // Вставка коктейля и ингредиентов
    suspend fun insertCocktail(cocktail: CocktailEntity, ingredients: List<IngredientEntity>) {
        cocktailDao.insertCocktail(cocktail, ingredients)
    }

    // Получение случайного коктейля с ингредиентами
    suspend fun getRandomCocktail(): CocktailWithIngredients? {
        return cocktailDao.getRandomCocktail()
    }

    // Поиск коктейлей по имени
    suspend fun searchCocktailsByName(query: String): List<CocktailWithIngredients> {
        return cocktailDao.searchCocktailsByName(query)
    }

    // Получение коктейля по ID
    suspend fun getCocktailById(id: Int): CocktailWithIngredients? {
        return cocktailDao.getCocktailById(id)
    }

    // Удаление коктейля
    suspend fun deleteCocktail(cocktail: CocktailEntity) {
        cocktailDao.deleteIngredientsByCocktailId(cocktail.id) // Удаляем ингредиенты
        cocktailDao.deleteCocktail(cocktail) // Удаляем сам коктейль
    }
}
