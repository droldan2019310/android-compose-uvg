package com.zezzi.eventzezziapp.ui.meals.view

import com.zezzi.eventzezziapp.data.networking.response.DishResponse

data class MealsDetailsUiState(
    val meals: List<DishResponse>,
    val loading: Boolean = false
)