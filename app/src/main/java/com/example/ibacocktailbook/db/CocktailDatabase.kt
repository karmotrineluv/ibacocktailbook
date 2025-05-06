package com.example.ibacocktailbook.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [CocktailEntity::class, IngredientEntity::class],
    version = 2,
    exportSchema = false
)
abstract class CocktailDatabase : RoomDatabase() {

    abstract fun cocktailDao(): CocktailDao

    companion object {
        @Volatile private var INSTANCE: CocktailDatabase? = null

        /** Возвращает единый экземпляр базы данных */
        fun getInstance(context: Context): CocktailDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CocktailDatabase::class.java,
                    "cocktail_database"
                )
                    // .addMigrations(MIGRATION_1_2)  // если нужно, добавьте миграции
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
