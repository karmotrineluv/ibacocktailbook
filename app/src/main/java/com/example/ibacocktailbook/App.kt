package com.example.ibacocktailbook

import android.app.Application
import androidx.room.Room
import com.example.ibacocktailbook.db.CocktailDatabase
import com.example.ibacocktailbook.db.CocktailEntity
import com.example.ibacocktailbook.db.DatabaseInitializer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class App : Application() {

    lateinit var database: CocktailDatabase
        private set

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            CocktailDatabase::class.java,
            "cocktail_database"
        ).build()

        CoroutineScope(Dispatchers.IO).launch {
            DatabaseInitializer.initialize(database)
        }
    }
}
