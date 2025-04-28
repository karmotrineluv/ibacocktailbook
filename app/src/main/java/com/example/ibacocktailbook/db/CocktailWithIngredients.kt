package com.example.ibacocktailbook.db
import androidx.room.Embedded
import androidx.room.Relation

data class CocktailWithIngredients(
    @Embedded val cocktail: CocktailEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "cocktailId"
    )
    val ingredients: List<IngredientEntity>
)
