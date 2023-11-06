package com.zezzi.eventzezziapp.data.networking.response
import com.google.gson.annotations.SerializedName

data class DishCategoryResponse(val meals: List<DishResponse>)

data class DishResponse(
    @SerializedName("idMeal") val id: Number,
    @SerializedName("strMeal") val name: String,
    @SerializedName("strMealThumb") val imageUrl: String,
)