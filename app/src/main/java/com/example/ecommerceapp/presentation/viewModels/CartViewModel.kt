package com.example.ecommerceapp.presentation.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.core.OperationStatus
import com.example.ecommerceapp.domain.model.Product
import com.example.ecommerceapp.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class CartViewModel(
    private val repository: ProductsRepository
) : ViewModel() {

    private val _allAddedProducts = MutableStateFlow<List<Product>>(emptyList())
    val allAddedProducts: StateFlow<List<Product>> = _allAddedProducts
    private val _showError = MutableSharedFlow<String>()
    val showError = _showError.asSharedFlow()


    fun showAllAddedProduct() = viewModelScope.launch {
        Log.d("CartViewModel", "Fetching all added products...")
       when(val status = repository.getAllAddedProducts()) {
           is OperationStatus.Failure -> {
               _showError.emit(status.exception.toString())
           }
           is OperationStatus.Success -> {
               _allAddedProducts.emit(status.value)
           }
       }
    }

    fun deleteAddedProduct(product: Product) = viewModelScope.launch {
        Log.d("CartViewModel", "Attempting to delete product: ${product.title}")
        when(val status = repository.deleteFromCart(product)) {
            is OperationStatus.Failure -> {
                _showError.emit(status.exception.toString())
            }
            is OperationStatus.Success -> {
                showAllAddedProduct()
            }
        }

    }
}
