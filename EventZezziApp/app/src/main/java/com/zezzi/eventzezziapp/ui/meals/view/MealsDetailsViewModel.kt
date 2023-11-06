package com.zezzi.eventzezziapp.ui.meals.view

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.zezzi.eventzezziapp.data.networking.response.DishResponse
import com.zezzi.eventzezziapp.data.networking.response.MealResponse
import com.zezzi.eventzezziapp.data.repository.FilterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class MealsDetailsViewModel(): ViewModel() {
    private val firestore = FirebaseFirestore.getInstance()
    private val mealsCollection = firestore.collection("meals")

    private var _meals = MutableStateFlow<List<DishResponse>>(emptyList())
    val meals = _meals.asStateFlow()



    fun getFilter(meal:String) {


        val categoryReference = firestore.collection("categories").document(meal)
        viewModelScope.launch {
            try {
                val querySnapshot = mealsCollection
                    .whereEqualTo("category_id", categoryReference) .get()
                    .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val documents = task.result?.documents
                        val mealsList = mutableListOf<DishResponse>()
                        if (documents != null) {
                            // documents contiene una lista de documentos de la colección "categories"
                            for (document in documents) {
                                // Accede a los datos del documento
                                val data = document.data
                                Log.d("DishesCategoriesViewModel", "Response for category  ${data}")

                                val dishResponse = DishResponse(
                                    id = data?.get("idMeal") as Number,
                                    name = data["strMeal"] as String,
                                    imageUrl = data["strMealThumb"] as String
                                )

                                mealsList.add(dishResponse)
                            }

                        } else {
                            // La colección "categories" está vacía

                        }

                        _meals.value = mealsList

                    } else {
                        // Manejar el error si la operación de obtención no fue exitosa
                        val exception = task.exception
                        if (exception != null) {
                            // Maneja la excepción
                        }
                    }
                }


            } catch (e: Exception) {
                // Maneja cualquier error
                e.printStackTrace()
            }
        }
    }
}