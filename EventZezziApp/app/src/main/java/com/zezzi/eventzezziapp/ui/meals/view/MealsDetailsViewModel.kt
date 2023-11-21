package com.zezzi.eventzezziapp.ui.meals.view

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zezzi.eventzezziapp.data.repository.FilterRepository
import kotlinx.coroutines.launch


class MealsDetailsViewModel(private val repository: FilterRepository = FilterRepository()): ViewModel() {
    var categoryUiState by mutableStateOf(MealsDetailsUiState(emptyList()))
        private set

    fun getFilter(meal:String) {
        categoryUiState = MealsDetailsUiState(emptyList(), loading = true)

        viewModelScope.launch {
            val response = repository.getFilter(meal)
            if (response != null) {
                if (response.meals != null && response.meals.isNotEmpty()) {
                    categoryUiState = MealsDetailsUiState(
                        meals = response.meals
                    )
                    Log.d("DishesCategoriesViewModel", "Response for category $meal: ${categoryUiState.meals}")
                } else {
                    // Manejar el caso en el que la lista de platos esté vacía
                    Log.e("DishesCategoriesViewModel", "dishessssssssss $response")
                }
            } else {
                // Manejar el caso en el que la respuesta sea nula
                Log.e("DishesCategoriesViewModel", "Response is null for category $meal")
            }
        }
    }
}