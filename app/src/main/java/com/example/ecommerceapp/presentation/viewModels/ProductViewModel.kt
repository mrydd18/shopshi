package com.example.ecommerceapp.presentation.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.core.OperationStatus
import com.example.ecommerceapp.domain.model.Product
import com.example.ecommerceapp.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: ProductsRepository) : ViewModel() {

    private var _allProductsList = MutableStateFlow<List<Product>>(emptyList())
    val allProductsList: StateFlow<List<Product>> = _allProductsList

    private var _addedToCart = MutableStateFlow(false)
    val addedToCart = _addedToCart.asStateFlow()

    init {
        getAllProductsList()
    }

     private fun getAllProductsList() = viewModelScope.launch {
        Log.d("ProductViewModel", "Fetching all products...")  // Log when the fetch starts

        when (val result = repository.getProducts()) {
            is OperationStatus.Success -> {
                Log.d("ProductViewModel", "Products fetched successfully. Emitting list of products...")
                Log.d("ProductViewModel", "Fetched result: ${result.value}")  // Log the fetched result
                _allProductsList.emit(result.value)  // Emit the fetched product list
            }
            is OperationStatus.Failure -> {
                Log.e("ProductViewModel", "Error Fetching Products: ${result.exception}")
            }
        }
    }

    fun saveToCart(product: Product) = viewModelScope.launch {
        product.let { repository.addToCart(product) }
    }
}


