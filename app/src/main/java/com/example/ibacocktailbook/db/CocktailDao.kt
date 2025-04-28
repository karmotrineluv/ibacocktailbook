package com.example.ibacocktailbook.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CocktailDao {

    // Получаем все коктейли с их ингредиентами (используется для LiveData)
    @Transaction
    @Query("SELECT * FROM cocktails")
    fun getAllCocktails(): LiveData<List<CocktailWithIngredients>>

    // Синхронный метод для получения всех коктейлей
    @Transaction
    @Query("SELECT * FROM cocktails")
    suspend fun getAllCocktailsList(): List<CocktailEntity>  // Теперь возвращает List<CocktailEntity>

    // Вставка коктейля и ингредиентов
    @Transaction
    suspend fun insertCocktail(cocktail: CocktailEntity, ingredients: List<IngredientEntity>) {
        val cocktailId = insertCocktail(cocktail) // Вставляем коктейль и получаем его ID
        ingredients.forEach { ingredient ->
            insertIngredient(ingredient.copy(cocktailId = cocktailId.toInt())) // Вставляем ингредиенты с привязкой к коктейлю
        }
    }

    // Вставка ингредиентов
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIngredients(ingredients: List<IngredientEntity>)

    // Вставка коктейля
    @Insert
    suspend fun insertCocktail(cocktail: CocktailEntity): Long

    // Вставка ингредиента
    @Insert
    suspend fun insertIngredient(ingredient: IngredientEntity)

    // Получаем случайный коктейль с ингредиентами
    @Transaction
    @Query("SELECT * FROM cocktails ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomCocktail(): CocktailWithIngredients?

    // Поиск коктейлей по имени
    @Transaction
    @Query("SELECT * FROM cocktails WHERE name LIKE '%' || :searchQuery || '%'")
    suspend fun searchCocktailsByName(searchQuery: String): List<CocktailWithIngredients>

    // Получаем коктейль по ID с ингредиентами
    @Transaction
    @Query("SELECT * FROM cocktails WHERE id = :id")
    suspend fun getCocktailById(id: Int): CocktailWithIngredients?

    // Удаляем коктейль
    @Delete
    suspend fun deleteCocktail(cocktail: CocktailEntity)

    // Удаляем ингредиенты по ID коктейля (для каскадного удаления)
    @Query("DELETE FROM ingredients WHERE cocktailId = :cocktailId")
    suspend fun deleteIngredientsByCocktailId(cocktailId: Int)
}
