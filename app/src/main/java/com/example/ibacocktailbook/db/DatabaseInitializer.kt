package com.example.ibacocktailbook.db

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object DatabaseInitializer {

    suspend fun initialize(database: CocktailDatabase) {
        Log.d("DatabaseInitializer", "Adding new cocktails...")
        val dao = database.cocktailDao()

        withContext(Dispatchers.IO) {
            val existingNames = dao.getAllCocktailsList()
                .map { it.name }
                .toSet()

            val cocktailsToInsert = listOf(

                        CocktailEntity(
                            name = "Alexander",
                            type = "Unforgettables",
                            imageUrl = "alexander",
                            description = "",
                            isFavorite = false,
                            instructions = "Pour all ingredients into cocktail shaker filled with ice cubes. Shake and strain into a chilled cocktail glass. Sprinkle fresh ground nutmeg on top."
                        ),
                        CocktailEntity(
                            name = "Americano",
                            type = "Unforgettables",
                            imageUrl = "americano",
                            description = "",
                            isFavorite = false,
                            instructions = "Mix the ingredients directly in an old fashioned glass filled with ice cubes. Add a splash of Soda Water. Stir gently."
                        ),
                        CocktailEntity(
                            name = "Angel Face",
                            type = "Unforgettables",
                            imageUrl = "angel_face",
                            description = "",
                            isFavorite = false,
                            instructions = "Pour all ingredients into cocktail shaker filled with ice cubes. Shake and strain into a chilled cocktail glass."
                        ),

                        CocktailEntity(
                            name = "Aviation",
                            type = "Unforgettables",
                            imageUrl = "aviation",
                            description = "",
                            isFavorite = false,
                            instructions = "Add all ingredients into a cocktail shaker. Shake with cracked ice and strain into a chilled cocktail glass. Garnish with Maraschino Cherry."
                        ),

                        CocktailEntity(
                            name = "Bee’s Knees",
                            type = "New Era",
                            imageUrl = "bees_knees",
                            description = "",
                            isFavorite = false,
                            instructions = "Stir honey with lemon and orange juices until it dissolves, add gin and shake with ice. Strain into a chilled cocktail glass."
                        ),

                        CocktailEntity(
                            name = "Bellini",
                            type = "Contemporary Classics",
                            imageUrl = "bellini",
                            description = "",
                            isFavorite = false,
                            instructions = "Pour peach puree into the mixing glass with ice, add the Prosecco wine. Stir gently and pour in a chilled flute glass."
                        ),

                        CocktailEntity(
                            name = "Between the Sheets",
                            type = "Unforgettables",
                            imageUrl = "between_the_sheets",
                            description = "",
                            isFavorite = false,
                            instructions = "Add all ingredients into a cocktail shaker. Shake with ice and strain into a chilled cocktail glass."
                        ),

                        CocktailEntity(
                            name = "Black Russian",
                            type = "Contemporary Classics",
                            imageUrl = "black_russian",
                            description = "",
                            isFavorite = false,
                            instructions = "Pour the ingredients into the old fashioned glass filled with ice cubes. Stir gently. strain ingredients into old fashioned glass filled with ice."
                        ),

                        CocktailEntity(
                            name = "Bloody Mary",
                            type = "Contemporary Classics",
                            imageUrl = "bloody_mary",
                            description = "",
                            isFavorite = false,
                            instructions = "Stir gently all the ingredients in a mixing glass with ice, pour into rocks glass. Garnish with celery and lemon wedge"
                        ),

                        CocktailEntity(
                            name = "Boulevardier",
                            type = "Unforgettables",
                            imageUrl = "boulevardier",
                            description = "",
                            isFavorite = false,
                            instructions = "Pour all ingredients into mixing glass with ice cubes. Stir well. Strain into chilled cocktail glass. Garnish with a orange zest, optionally a lemon zest."
                        ),

                        CocktailEntity(
                            name = "Bramble",
                            type = "New Era",
                            imageUrl = "bramble",
                            description = "",
                            isFavorite = false,
                            instructions = "Pour all ingredients into cocktail shaker except the Crème de Mûre, shake well with ice, strain into chilled old fashioned glass filled with crushed ice, then pour the blackberry liqueur (Crème de Mûre) over the top of the drink, in a circular motion. Garnish optionally with a lemon slice and blackberries."
                        ),

                        CocktailEntity(
                            name = "Brandy Crusta",
                            type = "Unforgettables",
                            imageUrl = "brandy_crusta",
                            description = "",
                            isFavorite = false,
                            instructions = "Mix together all ingredients with ice cubes in a mixing glass and strain into a prepared slim cocktail glass. Rub a slice of orange (or lemon) around the rim of the glass and dip it in pulverized white sugar, so that the sugar will adhere to the edge of the glass. Carefully curling place the orange/lemon peel around the inside of the glass."
                        ),

                        CocktailEntity(
                            name = "Caipirinha",
                            type = "Contemporary Classics",
                            imageUrl = "caipirinha",
                            description = "",
                            isFavorite = false,
                            instructions = "Place lime and sugar into a double old fashioned glass and muddle gently. Fill the glass with cracked ice and add Cachaça. Stir gently to involve ingredients."
                        ),

                        CocktailEntity(
                            name = "Canchanchara",
                            type = "New Era",
                            imageUrl = "canchanchara",
                            description = "",
                            isFavorite = false,
                            instructions = "Mix honey with water and lime juice and spread the mixture on the bottom and sides of the glass.  Add cracked ice, and then the rum. End by energetically stirring from bottom to top. Garnish with a lime wedge."
                        ),

                        CocktailEntity(
                            name = "Cardinale",
                            type = "Contemporary Classics",
                            imageUrl = "cardinale",
                            description = "",
                            isFavorite = false,
                            instructions = "Pour all ingredients into mixing glass with ice cubes. Stir well. Strain into chilled cocktail glass. Garnish with a lemon zest."
                        ),

                        CocktailEntity(
                            name = "Casino",
                            type = "Unforgettables",
                            imageUrl = "casino",
                            description = "",
                            isFavorite = false,
                            instructions = "Pour all ingredients into cocktails shaker, shake well with ice, strain into chilled rocks glass with ice. Garnish with a lemon zest and a maraschino cherry."
                        ),

                        CocktailEntity(
                            name = "Champagne Cocktail",
                            type = "Contemporary Classics",
                            imageUrl = "champagne_cocktail",
                            description = "",
                            isFavorite = false,
                            instructions = "Place the sugar cube with 2 dashes of bitters in a large Champagne glass, add the cognac. Pour gently chilled Champagne. Garnish with orange zest and maraschino cherry."
                        ),

                        CocktailEntity(
                            name = "Chartreuse Swizzle",
                            type = "New Era",
                            imageUrl = "chartreuse_swizzle",
                            description = "",
                            isFavorite = false,
                            instructions = "Pour all ingredients into a tall glass, add pebble ice. With the help of a swizzle stick (or cocktail spoon) mix vigorously, complete by filling the glass with more pebble ice. Garnish with mint leaves and grated nutmeg."
                        ),

                        CocktailEntity(
                            name = "Clover Club",
                            type = "Unforgettables",
                            imageUrl = "clover_club",
                            description = "",
                            isFavorite = false,
                            instructions = "Pour all ingredients into cocktails shaker, shake well with ice, strain into chilled cocktail glass. Garnish with fresh raspberries."
                        ),

                        CocktailEntity(
                            name = "Corpse Reviver #2",
                            type = "Contemporary Classics",
                            imageUrl = "corpse_reviver",
                            description = "",
                            isFavorite = false,
                            instructions = "Pour all ingredients into shaker with ice. Shake well and strain in chilled cocktail glass."
                        ),

                        CocktailEntity(
                            name = "Cosmopolitan",
                            type = "Contemporary Classics",
                            imageUrl = "cosmopolitan",
                            description = "",
                            isFavorite = false,
                            instructions = "Add all ingredients into cocktail shaker filled with ice. Shake well and strain into large cocktail glass. Garnish with lemon twist."
                        ),

                        CocktailEntity(
                            name = "Cuba Libre",
                            type = "Contemporary Classics",
                            imageUrl = "cuba_libre",
                            description = "",
                            isFavorite = false,
                            instructions = "Build all ingredients in a highball glass filled with ice. Garnish with lime wedge."
                        ),

                        CocktailEntity(
                            name = "Daiquiri",
                            type = "The unforgettables",
                            imageUrl = "daiquiri",
                            description = "",
                            isFavorite = false,
                            instructions = "In a cocktail shaker add all ingredients. Stir well to dissolve the sugar. Add ice and shake. Strain into chilled cocktail glass."
                        ),

                        CocktailEntity(
                            name = "Dark ‘N’ Stormy",
                            type = "New Era",
                            imageUrl = "dark_n_stormy",
                            description = "",
                            isFavorite = false,
                            instructions = "In a highball glass filled with ice pour the ginger beer and top floating with the Rum. Garnish with a lime wedge or slice."
                        ),

                        CocktailEntity(
                            name = "Don’s Special Daiquiri",
                            type = "New Era",
                            imageUrl = "special_daiquiri",
                            description = "",
                            isFavorite = false,
                            instructions = "Blend for a few seconds in a milkshake mixer with  crushed ice and pour into a footed copo glass. Fill the glass with more crushed ice. Garnish with 1/2 passion fruit"
                        ),

                        CocktailEntity(
                            name = "Dry Martini",
                            type = "The unforgettables",
                            imageUrl = "dry_martini",
                            description = "",
                            isFavorite = false,
                            instructions = "Pour all ingredients into mixing glass with ice cubes. Stir well. Strain into chilled martini cocktail glass. Squeeze oil from lemon peel onto the drink, or garnish with a green olives if requested."
                        ),

                        CocktailEntity(
                            name = "Espresso Martini",
                            type = "New Era",
                            imageUrl = "espresso_martini",
                            description = "",
                            isFavorite = false,
                            instructions = "Pour all ingredients into cocktail shaker, shake well with ice, strain into chilled cocktail glass. Garnish with 3 coffee beans"
                        ),

                        CocktailEntity(
                            name = "Fernandito",
                            type = "New Era",
                            imageUrl = "fernandito",
                            description = "",
                            isFavorite = false,
                            instructions = "Pour the Fernet Branca into a double old fashioned glass with ice, fill the glass up with Cola. Gently stir."
                        ),

                        CocktailEntity(
                            name = "French 75",
                            type = "Contemporary Classics",
                            imageUrl = "french75",
                            description = "",
                            isFavorite = false,
                            instructions = "Pour all the ingredients, except Champagne, into a shaker. Shake well and strain into a Champagne flute. Top up with Champagne. Stir gently."
                        ),

                        CocktailEntity(
                            name = "French Connection",
                            type = "Contemporary Classics",
                            imageUrl = "french_connection",
                            description = "",
                            isFavorite = false,
                            instructions = "Pour all ingredients directly into old fashioned glass filled with ice cubes. Stir gently."
                        ),

                        CocktailEntity(
                            name = "French Martini",
                            type = "New Era",
                            imageUrl = "french_martini",
                            description = "",
                            isFavorite = false,
                            instructions = "Pour all ingredients into cocktail shaker, shake well with ice, strain into chilled cocktail glass. Squeeze oil from lemon peel onto the drink."
                        ),

                        CocktailEntity(
                            name = "Garibaldi",
                            type = "Contemporary Classics",
                            imageUrl = "garibaldi",
                            description = "",
                            isFavorite = false,
                            instructions = "Build all ingredients in a highball glass filled with ice. Garnish with an orange wedge."
                        ),

                        CocktailEntity(
                            name = "Gin Basil Smash",
                            type = "New Era",
                            imageUrl = "gin_basil_smash",
                            description = "",
                            isFavorite = false,
                            instructions = "Add all ingredients into shaker with ice. Shake vigorously and pour into chilled cocktail glass."
                        ),

                        CocktailEntity(
                            name = "Gin Fizz",
                            type = "The unforgettables",
                            imageUrl = "gin_fizz",
                            description = "",
                            isFavorite = false,
                            instructions = "Shake all ingredients with ice except soda water. Pour into thin tall Tumbler glass , top with a splash soda water. Garnish with lemon slice, optional lemon zest."
                        ),

                        CocktailEntity(
                            name = "Grand Margarita",
                            type = "New Era",
                            imageUrl = "grand_margarita",
                            description = "",
                            isFavorite = false,
                            instructions = "Rim the rock glass with good quality sea salt. Pour the ingredients into the  shaker. Add ice to both glass and shaker. Shake hard for 10 seconds. Strain the drink into the glass."
                        ),

                        CocktailEntity(
                            name = "Grasshopper",
                            type = "Contemporary Classics",
                            imageUrl = "grasshopper",
                            description = "",
                            isFavorite = false,
                            instructions = "Pour all ingredients into shaker filled with ice. Shake briskly for few seconds. Strain into chilled cocktail glass. Optinally garnish with mint leaves"
                        ),

                        CocktailEntity(
                            name = "Hanky Panky",
                            type = "The unforgettables",
                            imageUrl = "hanky_panky",
                            description = "",
                            isFavorite = false,
                            instructions = "Pour all ingredients into mixing glass with ice cubes. Stir well. Strain into chilled cocktail glass. Garnish with orange zest."
                        ),

                        CocktailEntity(
                            name = "Hemingway Special",
                            type = "Contemporary Classics",
                            imageUrl = "hemingway_special",
                            description = "",
                            isFavorite = false,
                            instructions = "Pour all ingredients into a shaker with ice. Shake well and strain into a large cocktail glass."
                        ),

                        CocktailEntity(
                            name = "Horse’s Neck",
                            type = "Contemporary Classics",
                            imageUrl = "horses_neck",
                            description = "",
                            isFavorite = false,
                            instructions = "Pour Cognac and ginger ale directly into highball glass with ice cubes. Stir gently. If preferred, add dashes of Angostura Bitter. Garnish with rind of one lemon spiral."
                        ),

                        CocktailEntity(
                            name = "IBA Tiki",
                            type = "New Era",
                            imageUrl = "tiki",
                            description = "",
                            isFavorite = false,
                            instructions = "In a cocktail shaker muddle a thin slice of Gengibre, Pour all other ingredients. Shake vigorously with ice. Strain into a chilled Tiki glass filled with pebbled ice. Garnish with citruses and dehydrated pineapple slice."
                        ),
                )
            val missing = cocktailsToInsert.filter { it.name !in existingNames }
            Log.d("DatabaseInitializer", "Need to insert ${missing.size} new cocktails")

            missing.forEach { cocktail ->
                // Вставляем коктейль и получаем его сгенерированный ID
                val cocktailId = dao.insertCocktail(cocktail).toInt()
                Log.d("DatabaseInitializer", "Inserted cocktail: ${cocktail.name} (ID = $cocktailId)")

                // Готовим список ингредиентов, устанавливая правильный cocktailId
                val ingredients = when (cocktail.name) {
                            "Alexander" -> listOf(
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Cognac",
                                    amount = "30 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Crème de Cacao",
                                    amount = "30 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Fresh Cream",
                                    amount = "30 ml"
                                ),
                            )

                            "Americano" -> listOf(
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Campari",
                                    amount = "30 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Sweet Vermouth",
                                    amount = "30 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Soda water",
                                    amount = "splash"
                                )
                            )

                            "Angel Face" -> listOf(
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Gin",
                                    amount = "30 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Apricot Brandy",
                                    amount = "30 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Calvados",
                                    amount = "30 ml"
                                ),
                            )

                            "Aviation" -> listOf(
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Gin",
                                    amount = "45 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Maraschino Luxardo",
                                    amount = "15 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Fresh Lemon Juice",
                                    amount = "15 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Crème de Violette",
                                    amount = "1 bar spoon"
                                ),
                            )

                            "Bee’s Knees" -> listOf(
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Dry Gin",
                                    amount = "52,5 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Fresh Lemon Juice",
                                    amount = "22,5 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Fresh Orange Juice",
                                    amount = "22,5 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Honey Syrup",
                                    amount = "2 teaspoons"
                                ),
                            )

                            "Bellini" -> listOf(
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Prosecco",
                                    amount = "100 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "White Peach Puree",
                                    amount = "50 ml"
                                ),
                            )

                            "Between the Sheets" -> listOf(
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "White Rum",
                                    amount = "30 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Cognac",
                                    amount = "30 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Triple Sec",
                                    amount = "30 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Fresh Lemon Juice",
                                    amount = "20 ml"
                                ),
                            )

                            "Black Russian" -> listOf(
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Vodka",
                                    amount = "50 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Coffee Liqueur",
                                    amount = "20 ml"
                                ),
                            )

                            "Bloody Mary" -> listOf(
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Vodka",
                                    amount = "45 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Tomato Juice",
                                    amount = "90 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Fresh Lemon Juice",
                                    amount = "15 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Worcestershire Sauce",
                                    amount = "2 dashes"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Tabasco, Celery Salt, Pepper",
                                    amount = "Up to taste"
                                ),
                            )

                            "Boulevardier" -> listOf(
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Bourbon",
                                    amount = "45 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Campari",
                                    amount = "30 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Sweet Red Vermouth",
                                    amount = "30 ml"
                                ),
                            )

                            "Bramble" -> listOf(
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Gin",
                                    amount = "50 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Fresh Lemon Juice",
                                    amount = "25 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Sugar Syrup",
                                    amount = "12,5 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Crème de Mûre",
                                    amount = "15 ml"
                                ),
                            )

                            "Brandy Crusta" -> listOf(
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Brandy",
                                    amount = "52,5 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Maraschino Luxardo",
                                    amount = "7,5 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Curacao",
                                    amount = "1 Bar Spoon"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Fresh Lemon Juice",
                                    amount = "15 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Simple Syrup",
                                    amount = "1 Bar Spoon"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Aromatic Bitters",
                                    amount = "2 Dashes"
                                ),
                            )

                            "Caipirinha" -> listOf(
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Cachaça",
                                    amount = "60 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Lime",
                                    amount = "1, cut into wedges"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "White Cane Sugar",
                                    amount = "4 Teaspoons"
                                ),
                            )

                            "Canchanchara" -> listOf(
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Cuban Aguardiente",
                                    amount = "60 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Fresh Lime Juice",
                                    amount = "15 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Raw Honey",
                                    amount = "15 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Water",
                                    amount = "50 ml"
                                )
                            )

                            "Cardinale" -> listOf(
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Gin",
                                    amount = "40 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Dry Vermouth",
                                    amount = "20 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Bitter Campari",
                                    amount = "10 ml"
                                ),
                            )

                            "Casino" -> listOf(
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Old Tom Gin",
                                    amount = "40 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Maraschino Luxardo",
                                    amount = "10 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Fresh Lemon Juice",
                                    amount = "10 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Orange Bitters",
                                    amount = "2 Dashes"
                                ),
                            )

                            "Champagne Cocktail" -> listOf(
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Chilled Champagne",
                                    amount = "90 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Cognac",
                                    amount = "10 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Angostura bitters",
                                    amount = "2 Dashes"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Grand Marnier",
                                    amount = "Few drops"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Sugar cube",
                                    amount = "1"
                                ),
                            )

                            "Chartreuse Swizzle" -> listOf(
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Green Chartreuse",
                                    amount = "45 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Fresh Pineapple Juice",
                                    amount = "30 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Fresh Lime Juice",
                                    amount = "22.5 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Falernum",
                                    amount = "15 ml"
                                ),
                            )

                            "Clover Club" -> listOf(
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Gin",
                                    amount = "45 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Raspberry Syrup",
                                    amount = "15 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Fresh Lemon Juice",
                                    amount = "15 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Egg White",
                                    amount = "Few drops"
                                ),
                            )

                            "Corpse Reviver #2" -> listOf(
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Gin",
                                    amount = "30 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Cointreau",
                                    amount = "30 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Lillet Blanc",
                                    amount = "30 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Fresh Lemon Juice",
                                    amount = "30 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Absinthe",
                                    amount = "1 Dash"
                                ),
                            )

                            "Cosmopolitan" -> listOf(
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Vodka Citron",
                                    amount = "40 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Cointreau",
                                    amount = "15 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Fresh Lime Juice",
                                    amount = "15 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Cranberry Juice",
                                    amount = "30 ml"
                                ),
                            )

                            "Cuba Libre" -> listOf(
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "White Rum",
                                    amount = "50 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Cola",
                                    amount = "120 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Fresh Lime Juice",
                                    amount = "10 ml"
                                ),
                            )

                            "Daiquiri" -> listOf(
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "White Rum",
                                    amount = "60 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Fresh Lime Juice",
                                    amount = "20 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Superfine Sugar",
                                    amount = "2 Bar Spoons"
                                ),
                            )

                            "Dark ‘N’ Stormy" -> listOf(
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Goslings Rum",
                                    amount = "60 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Ginger Beer",
                                    amount = "100 ml"
                                ),
                            )

                            "Don’s Special Daiquiri" -> listOf(
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Gold Jamaican Rum",
                                    amount = "30 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Cuban Rum",
                                    amount = "15 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Passion Fruit Syrup",
                                    amount = "15 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Fresh lime juice",
                                    amount = "15 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Honey Syrup",
                                    amount = "15 ml"
                                ),
                            )

                            "Dry Martini" -> listOf(
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Gin",
                                    amount = "60 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Dry Vermouth",
                                    amount = "10 ml"
                                ),
                            )
                            "Espresso Martini" -> listOf(
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Vodka",
                                    amount = "50 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Kahlúa",
                                    amount = "30 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Sugar Syrup",
                                    amount = "10 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Strong Espresso",
                                    amount = "1"
                                ),
                            )
                            "Fernandito" -> listOf(
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Fernet Branca",
                                    amount = "50 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Cola",
                                    amount = "fill up"
                                ),
                            )

                            "French 75" -> listOf(
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Gin",
                                    amount = "30 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Fresh Lemon Juice",
                                    amount = "15 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Sugar Syrup",
                                    amount = "15 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Champagne",
                                    amount = "60 ml"
                                ),
                            )

                            "French Connection" -> listOf(
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Cognac",
                                    amount = "35 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Amaretto",
                                    amount = "35 ml"
                                ),
                            )

                            "Garibaldi" -> listOf(
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Bitter Campari",
                                    amount = "45 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Freshly Squeezed Orange Juice",
                                    amount = "120 ml"
                                ),
                            )

                            "Gin Basil Smash" -> listOf(
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Gin",
                                    amount = "60 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Freshly Squeezed Lemon Juice",
                                    amount = "22,5 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Sugar Syrup",
                                    amount = "22,5 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Basil leaves",
                                    amount = "10pcs ml"
                                ),
                            )
                            "Gin Fizz" -> listOf(
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Gin",
                                    amount = "45 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Fresh Lemon Juice",
                                    amount = "30 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Simple Syrup",
                                    amount = "10 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Soda Water",
                                    amount = "splash"
                                ),
                            )

                            "Grand Margarita" -> listOf(
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Tequila",
                                    amount = "45 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Grand Marnier",
                                    amount = "30 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Fresh Lime Juice",
                                    amount = "15 ml"
                                ),
                            )

                            "Grasshopper" -> listOf(
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Crème de Cacao",
                                    amount = "20 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Crème de Menthe",
                                    amount = "20 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Fresh Cream",
                                    amount = "25 ml"
                                ),
                            )

                            "Hanky Panky" -> listOf(
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "London Dry Gin",
                                    amount = "45 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Sweet Red Vermouth",
                                    amount = "45 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Fernet",
                                    amount = "7,5 ml"
                                ),
                            )

                            "Hemingway Special" -> listOf(
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Rum",
                                    amount = "60 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Grapefruit Juice",
                                    amount = "40 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Maraschino Luxardo",
                                    amount = "15 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Fresh Lime Juice",
                                    amount = "15 ml"
                                ),
                            )

                            "Horse’s Neck" -> listOf(
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Cognac",
                                    amount = "40 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Ginger Ale",
                                    amount = "120 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Angostura Bitters",
                                    amount = "Dash"
                                ),
                            )

                            "IBA Tiki" -> listOf(
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Ron Profundo Havana Club",
                                    amount = "30 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Ron Smoky Havana Club",
                                    amount = "30 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Amaretto",
                                    amount = "15 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Frangelico",
                                    amount = "5 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Maraschino Luxardo",
                                    amount = "5 drops"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Passion Fruit Puree",
                                    amount = "30 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Fresh Pineapple Juice",
                                    amount = "90 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Fresh Lime Juice",
                                    amount = "30 ml"
                                ),
                                IngredientEntity(
                                    cocktailId = cocktailId,
                                    name = "Gengibre Slice",
                                    amount = "1 pc"
                                ),
                            )


                            else -> emptyList()
                        }

                        ingredients.forEach { ingredient ->
                            dao.insertIngredient(ingredient)
                        }
                    }
                }
            }
        }