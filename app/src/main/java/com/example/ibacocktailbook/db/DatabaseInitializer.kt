package com.example.ibacocktailbook.db

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object DatabaseInitializer {

    suspend fun initialize(database: CocktailDatabase) {
        val dao = database.cocktailDao()

        // Здесь мы не можем напрямую использовать getAllCocktails(), потому что это LiveData.
        // Вместо этого, мы можем использовать getAllCocktailsList(), если хотим получить данные синхронно.

        // Переход от LiveData к синхронным данным через сессию корутин
        withContext(Dispatchers.IO) {
            val cocktails = dao.getAllCocktailsList() // Возвращает List<CocktailEntity>

            if (cocktails.isEmpty()) {
                withContext(Dispatchers.IO) {
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
                            imageUrl = "corpse_reviver2",
                            description = "",
                            isFavorite = false,
                            instructions = "Pour all ingredients into shaker with ice. Shake well and strain in chilled cocktail glass."
                        ),

                        )

                    cocktailsToInsert.forEach { cocktail ->
                        val cocktailId = dao.insertCocktail(cocktail).toInt()

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


                            else -> emptyList()
                        }

                        ingredients.forEach { ingredient ->
                            dao.insertIngredient(ingredient)
                        }
                    }
                }
            }
        }
    }
}