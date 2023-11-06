import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.zezzi.eventzezziapp.data.networking.response.MealResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CategoriesViewModel : ViewModel() {
    private val firestore = FirebaseFirestore.getInstance()
    private val categoriesCollection = firestore.collection("categories")

    private var _categories = MutableStateFlow<List<MealResponse>>(emptyList())
    val categories = _categories.asStateFlow()

    init {
        // Llama a una función para cargar las categorías al inicializar el ViewModel
        loadCategories()
    }

    private fun loadCategories() {
        viewModelScope.launch {
            try {
                val querySnapshot = categoriesCollection.get().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val documents = task.result?.documents
                        val categoriesList = mutableListOf<MealResponse>()
                        if (documents != null) {
                            // documents contiene una lista de documentos de la colección "categories"
                            for (document in documents) {
                                // Accede a los datos del documento
                                val data = document.data

                                val mealResponse = MealResponse(
                                    id = document.id,
                                    idC = data?.get("idCategory") as Number,
                                    name = data["strCategory"] as String,
                                    description = data["strCategoryDescription"] as String,
                                    imageUrl = data["strCategoryThumb"] as String
                                )

                                categoriesList.add(mealResponse)

                            }

                        } else {
                            // La colección "categories" está vacía
                        }

                        _categories.value = categoriesList

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