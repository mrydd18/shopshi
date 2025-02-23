package com.example.ecommerceapp.domain.usecases

import com.example.ecommerceapp.core.OperationStatus
import com.example.ecommerceapp.domain.model.Product
import com.example.ecommerceapp.domain.repository.ProductsRepository

class DeleteFromAddedUseCase(
    private val repository: ProductsRepository
) {
    suspend fun execute(product: Product): OperationStatus<Unit> {
        return repository.deleteFromCart(product)
    }
}