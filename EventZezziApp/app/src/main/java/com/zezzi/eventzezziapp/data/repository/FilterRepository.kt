package com.zezzi.eventzezziapp.data.repository

import com.zezzi.eventzezziapp.data.networking.MealsWebService
import com.zezzi.eventzezziapp.data.networking.response.DishCategoryResponse
import com.zezzi.eventzezziapp.data.networking.response.MealsCategoriesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilterRepository(private val webService: MealsWebService = MealsWebService()) {
    suspend fun getFilter(meal:String): DishCategoryResponse {
        return withContext(Dispatchers.IO) {
            webService.getFilter(meal)
        }
    }
}