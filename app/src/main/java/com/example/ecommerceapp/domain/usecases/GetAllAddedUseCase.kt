package com.example.ecommerceapp.domain.usecases

import com.example.ecommerceapp.core.OperationStatus
import com.example.ecommerceapp.domain.model.Product
import com.example.ecommerceapp.domain.repository.ProductsRepository

class GetAllAddedUseCase(
    private val repository: ProductsRepository
) {
    suspend fun execute(): OperationStatus<List<Product>> {
        return repository.getAllAddedProducts()
    }
}