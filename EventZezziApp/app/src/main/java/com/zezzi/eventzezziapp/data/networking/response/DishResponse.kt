package com.zezzi.eventzezziapp.data.networking.response
import com.google.gson.annotations.SerializedName

data class DishCategoryResponse(val categories: List<DishResponse>)

data class DishResponse(
    @SerializedName("idMeal") val id: String,
    @SerializedName("strMeal") val name: String,
    @SerializedName("strMealThumb") val imageUrl: String,
)