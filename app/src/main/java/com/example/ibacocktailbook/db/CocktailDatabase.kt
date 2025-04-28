package com.example.ibacocktailbook.db
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CocktailEntity::class, IngredientEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CocktailDatabase : RoomDatabase() {
    abstract fun cocktailDao(): CocktailDao
}
