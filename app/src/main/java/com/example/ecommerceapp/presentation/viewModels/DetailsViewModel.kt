package com.example.ecommerceapp.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.core.OperationStatus
import com.example.ecommerceapp.domain.model.Product
import com.example.ecommerceapp.domain.repository.ProductsRepository
import com.example.ecommerceapp.presentation.fragments.FragmentDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val repository: ProductsRepository

) : ViewModel() {


    private var _itemDetails = MutableStateFlow<Product?>(null)
    val itemDetails: StateFlow<Product?> = _itemDetails


    fun getItemById(productId: Int) = viewModelScope.launch {
        when ( val status = repository.getItemById(productId = productId)) {
            is OperationStatus.Success -> {
                _itemDetails.emit(status.value)
            }
            is OperationStatus.Failure -> {
                null
            }
        }

    }

}