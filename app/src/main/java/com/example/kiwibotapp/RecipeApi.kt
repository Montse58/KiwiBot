package com.example.kiwibotapp


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

// Data classes for the API request and response
data class IngredientsRequest(val ingredients: List<String>)
data class RecipeResponse(val recipe: String)

interface RecipeApi {
    @POST("/generate-recipe")
    fun generateRecipe(@Body ingredients: IngredientsRequest): Call<RecipeResponse>
}
