package com.example.ibacocktailbook.db
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredients")
data class IngredientEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val cocktailId: Int, // внешний ключ на CocktailEntity
    val name: String,
    val amount: String // например "45 ml", "1 dash"
)
