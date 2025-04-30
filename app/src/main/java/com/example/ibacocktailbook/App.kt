package com.example.ibacocktailbook

import android.app.Application
import androidx.room.Room
import com.example.ibacocktailbook.db.CocktailDatabase
import com.example.ibacocktailbook.db.CocktailEntity
import com.example.ibacocktailbook.db.DatabaseInitializer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class App : Application() {

    lateinit var database: CocktailDatabase
        private set

    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE cocktails ADD COLUMN isFavorite INTEGER NOT NULL DEFAULT 0")
        }
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            CocktailDatabase::class.java,
            "cocktail_database"
        )
            .addMigrations(MIGRATION_1_2)
            .build()

        CoroutineScope(Dispatchers.IO).launch {
            DatabaseInitializer.initialize(database)
        }
    }
}
