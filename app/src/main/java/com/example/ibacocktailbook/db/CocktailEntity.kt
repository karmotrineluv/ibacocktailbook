package com.example.ibacocktailbook.db
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cocktails")
data class CocktailEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val imageUrl: String,
    val description: String,
    val instructions: String,
    val type: String // Unforgettables, Contemporary, New Era, Custom
)
