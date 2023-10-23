package com.zezzi.eventzezziapp.ui.meals.view

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
            categoryUiState = MealsDetailsUiState(
                categories = repository.getFilter(meal).categories
            )
        }
    }
}